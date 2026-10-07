package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.data.local.PreferencesRepository
import com.example.ui.calculator.CalculatorScreen
import com.example.ui.calculator.CalculatorViewModel
import com.example.ui.calculator.CalculatorViewModelFactory
import com.example.ui.theme.ElecBillBdTheme

class MainActivity : ComponentActivity() {

    private val viewModel: CalculatorViewModel by viewModels {
        CalculatorViewModelFactory(PreferencesRepository(applicationContext))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val state by viewModel.state.collectAsState()

            ElecBillBdTheme(themeMode = state.themeMode) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    CalculatorScreen(
                        state = state,
                        onUnitsChanged = viewModel::onUnitsChanged,
                        onPresetSelected = viewModel::onPresetSelected,
                        onCalculate = viewModel::calculate,
                        onReset = viewModel::reset,
                        onLanguageSelected = viewModel::setLanguage,
                        onPlanSelected = viewModel::setSelectedPlan,
                        onToggleBreakdownExpanded = viewModel::toggleBreakdownExpanded,
                        onHistoryItemClick = viewModel::applyHistoryItem,
                        onClearHistory = viewModel::clearHistory,
                        onOpenTariffInfo = { viewModel.setShowTariffInfoSheet(true) },
                        onCloseTariffInfo = { viewModel.setShowTariffInfoSheet(false) },
                        onOpenSettings = { viewModel.setShowSettingsSheet(true) },
                        onCloseSettings = { viewModel.setShowSettingsSheet(false) },
                        onThemeChange = viewModel::setThemeMode,
                        onToggleDemandCharge = viewModel::toggleDemandCharge
                    )
                }
            }
        }
    }
}
