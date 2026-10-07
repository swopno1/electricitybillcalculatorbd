package com.example.ui.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.local.PreferencesRepository
import com.example.data.tariff.BangladeshResidentialTariff
import com.example.data.tariff.TariffPlan
import com.example.data.tariff.TariffRepository
import com.example.domain.calculator.ElectricityBillCalculator
import com.example.domain.model.AppLanguage
import com.example.domain.model.AppThemeMode
import com.example.domain.model.CalculationHistoryItem
import com.example.ui.util.AppStrings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

class CalculatorViewModel(
    private val preferencesRepository: PreferencesRepository,
    private val tariffRepository: TariffRepository = TariffRepository()
) : ViewModel() {

    private val _state = MutableStateFlow(
        CalculatorState(
            unitsInput = "100",
            selectedPlan = tariffRepository.getDefaultPlan(),
            language = preferencesRepository.getLanguage(),
            themeMode = preferencesRepository.getThemeMode(),
            includeDemandCharge = preferencesRepository.getIncludeDemandCharge(),
            sanctionedLoadKw = preferencesRepository.getSanctionedLoad(),
            history = preferencesRepository.getHistory()
        )
    )
    val state: StateFlow<CalculatorState> = _state.asStateFlow()

    init {
        // Automatically calculate initial default (100 units) so user sees immediate results
        calculate()
    }

    /**
     * Normalizes digits: converts Bengali digits to ASCII 0-9 for parsing.
     */
    private fun normalizeInputDigits(input: String): String {
        val bengaliToAsciiMap = mapOf(
            '০' to '0', '১' to '1', '২' to '2', '৩' to '3', '৪' to '4',
            '৫' to '5', '৬' to '6', '৭' to '7', '৮' to '8', '৯' to '9'
        )
        val sb = StringBuilder()
        for (char in input) {
            val converted = bengaliToAsciiMap[char] ?: char
            sb.append(converted)
        }
        return sb.toString()
    }

    fun onUnitsChanged(rawInput: String) {
        val cleaned = normalizeInputDigits(rawInput.trim())
        // Allow numeric and at most one decimal point
        if (cleaned.isEmpty() || cleaned.matches(Regex("^\\d*\\.?\\d*$"))) {
            _state.update {
                it.copy(
                    unitsInput = rawInput,
                    errorMessage = null
                )
            }
            // If valid number entered, calculate dynamically
            val parsed = cleaned.toDoubleOrNull()
            if (parsed != null && parsed in 0.0..50000.0) {
                performCalculation(parsed, addToHistory = false)
            }
        }
    }

    fun onPresetSelected(presetUnits: Double) {
        val formatted = if (presetUnits % 1.0 == 0.0) {
            presetUnits.toInt().toString()
        } else {
            presetUnits.toString()
        }
        _state.update {
            it.copy(
                unitsInput = formatted,
                errorMessage = null
            )
        }
        performCalculation(presetUnits, addToHistory = true)
    }

    fun calculate() {
        val raw = _state.value.unitsInput.trim()
        val currentLang = _state.value.language

        if (raw.isEmpty()) {
            _state.update {
                it.copy(
                    errorMessage = AppStrings.emptyInputError(currentLang),
                    billBreakdown = null
                )
            }
            return
        }

        val normalized = normalizeInputDigits(raw)
        val parsed = normalized.toDoubleOrNull()

        if (parsed == null) {
            _state.update {
                it.copy(
                    errorMessage = AppStrings.invalidNumberError(currentLang),
                    billBreakdown = null
                )
            }
            return
        }

        if (parsed < 0.0) {
            _state.update {
                it.copy(
                    errorMessage = AppStrings.negativeInputError(currentLang),
                    billBreakdown = null
                )
            }
            return
        }

        if (parsed > 50000.0) {
            _state.update {
                it.copy(
                    errorMessage = AppStrings.largeInputError(currentLang),
                    billBreakdown = null
                )
            }
            return
        }

        performCalculation(parsed, addToHistory = true)
    }

    private fun performCalculation(units: Double, addToHistory: Boolean) {
        val currentState = _state.value
        try {
            val breakdown = ElectricityBillCalculator.calculate(
                units = units,
                plan = currentState.selectedPlan,
                sanctionedLoadKw = currentState.sanctionedLoadKw,
                includeDemandCharge = currentState.includeDemandCharge
            )

            if (addToHistory && units > 0.0) {
                val historyItem = CalculationHistoryItem(
                    id = UUID.randomUUID().toString(),
                    timestamp = System.currentTimeMillis(),
                    units = units,
                    totalAmount = breakdown.totalAmount.toDouble(),
                    planNameEn = currentState.selectedPlan.nameEn,
                    planNameBn = currentState.selectedPlan.nameBn
                )
                preferencesRepository.addHistoryItem(historyItem)
                _state.update {
                    it.copy(
                        billBreakdown = breakdown,
                        errorMessage = null,
                        history = preferencesRepository.getHistory()
                    )
                }
            } else {
                _state.update {
                    it.copy(
                        billBreakdown = breakdown,
                        errorMessage = null
                    )
                }
            }
        } catch (e: Exception) {
            _state.update {
                it.copy(
                    errorMessage = e.message ?: "Calculation error",
                    billBreakdown = null
                )
            }
        }
    }

    fun reset() {
        val defaultPlan = BangladeshResidentialTariff.plan
        _state.update {
            it.copy(
                unitsInput = "100",
                selectedPlan = defaultPlan,
                includeDemandCharge = true,
                sanctionedLoadKw = 1.0,
                errorMessage = null,
                isBreakdownExpanded = true
            )
        }
        performCalculation(100.0, addToHistory = false)
    }

    fun setLanguage(language: AppLanguage) {
        preferencesRepository.setLanguage(language)
        _state.update { it.copy(language = language) }
    }

    fun toggleLanguage() {
        val nextLang = if (_state.value.language == AppLanguage.BANGLA) {
            AppLanguage.ENGLISH
        } else {
            AppLanguage.BANGLA
        }
        setLanguage(nextLang)
    }

    fun setThemeMode(mode: AppThemeMode) {
        preferencesRepository.setThemeMode(mode)
        _state.update { it.copy(themeMode = mode) }
    }

    fun setSelectedPlan(plan: TariffPlan) {
        _state.update { it.copy(selectedPlan = plan) }
        val currentUnits = normalizeInputDigits(_state.value.unitsInput).toDoubleOrNull()
        if (currentUnits != null) {
            performCalculation(currentUnits, addToHistory = false)
        }
    }

    fun toggleDemandCharge(include: Boolean) {
        preferencesRepository.setIncludeDemandCharge(include)
        _state.update { it.copy(includeDemandCharge = include) }
        val currentUnits = normalizeInputDigits(_state.value.unitsInput).toDoubleOrNull()
        if (currentUnits != null) {
            performCalculation(currentUnits, addToHistory = false)
        }
    }

    fun toggleBreakdownExpanded() {
        _state.update { it.copy(isBreakdownExpanded = !it.isBreakdownExpanded) }
    }

    fun applyHistoryItem(item: CalculationHistoryItem) {
        val formattedUnits = if (item.units % 1.0 == 0.0) {
            item.units.toInt().toString()
        } else {
            item.units.toString()
        }
        _state.update {
            it.copy(
                unitsInput = formattedUnits,
                errorMessage = null
            )
        }
        performCalculation(item.units, addToHistory = false)
    }

    fun clearHistory() {
        preferencesRepository.clearHistory()
        _state.update { it.copy(history = emptyList()) }
    }

    fun setShowTariffInfoSheet(show: Boolean) {
        _state.update { it.copy(showTariffInfoSheet = show) }
    }

    fun setShowSettingsSheet(show: Boolean) {
        _state.update { it.copy(showSettingsSheet = show) }
    }
}

class CalculatorViewModelFactory(
    private val preferencesRepository: PreferencesRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CalculatorViewModel::class.java)) {
            return CalculatorViewModel(preferencesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
