package com.example.ui.calculator

import com.example.data.tariff.BangladeshResidentialTariff
import com.example.data.tariff.TariffPlan
import com.example.domain.model.AppLanguage
import com.example.domain.model.AppThemeMode
import com.example.domain.model.BillBreakdown
import com.example.domain.model.CalculationHistoryItem

data class CalculatorState(
    val unitsInput: String = "100",
    val selectedPlan: TariffPlan = BangladeshResidentialTariff.plan,
    val includeDemandCharge: Boolean = true,
    val sanctionedLoadKw: Double = 1.0,
    val billBreakdown: BillBreakdown? = null,
    val errorMessage: String? = null,
    val language: AppLanguage = AppLanguage.BANGLA,
    val themeMode: AppThemeMode = AppThemeMode.SYSTEM,
    val isBreakdownExpanded: Boolean = true,
    val history: List<CalculationHistoryItem> = emptyList(),
    val showTariffInfoSheet: Boolean = false,
    val showSettingsSheet: Boolean = false
)
