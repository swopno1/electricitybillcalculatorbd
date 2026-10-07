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
import com.example.util.InterstitialAdManager
import com.google.android.gms.ads.MobileAds

class MainActivity : ComponentActivity() {

    private val viewModel: CalculatorViewModel by viewModels {
        CalculatorViewModelFactory(PreferencesRepository(applicationContext))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize Google Mobile Ads SDK as per AdMob integration guide
        try {
            val webViewCache = java.io.File(cacheDir, "WebView/Default/HTTP Cache/Code Cache/js")
            if (!webViewCache.exists()) {
                webViewCache.mkdirs()
            }
        } catch (_: Exception) {
        }

        try {
            val requestConfig = com.google.android.gms.ads.RequestConfiguration.Builder()
                .setTestDeviceIds(listOf(com.google.android.gms.ads.AdRequest.DEVICE_ID_EMULATOR))
                .build()
            MobileAds.setRequestConfiguration(requestConfig)

            MobileAds.initialize(this) {
                try {
                    InterstitialAdManager.preload(applicationContext)
                } catch (_: Exception) {
                }
            }
        } catch (_: Exception) {
            // Graceful fallback for environments without Play services
        }

        setContent {
            val state by viewModel.state.collectAsState()

            ElecBillBdTheme(themeMode = state.themeMode) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    CalculatorScreen(
                        state = state,
                        onUnitsChanged = viewModel::onUnitsChanged,
                        onPresetSelected = { preset ->
                            viewModel.onPresetSelected(preset)
                            InterstitialAdManager.showIfEligible(this@MainActivity)
                        },
                        onCalculate = {
                            viewModel.calculate()
                            InterstitialAdManager.showIfEligible(this@MainActivity)
                        },
                        onReset = {
                            viewModel.reset()
                            InterstitialAdManager.showIfEligible(this@MainActivity)
                        },
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
