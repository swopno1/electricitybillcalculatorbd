package com.example.ui.calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.AppLanguage
import com.example.ui.calculator.components.ActionButtonsRow
import com.example.ui.calculator.components.BillResultCard
import com.example.ui.calculator.components.DisclaimerCard
import com.example.ui.calculator.components.LanguageToggle
import com.example.ui.calculator.components.QuickPresetsRow
import com.example.ui.calculator.components.RecentHistoryCard
import com.example.ui.calculator.components.SlabBreakdownCard
import com.example.ui.calculator.components.TariffSelectorRow
import com.example.ui.calculator.components.UnitInputField
import com.example.ui.sheets.SettingsSheet
import com.example.ui.sheets.TariffInfoSheet
import com.example.ui.util.AppStrings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorScreen(
    state: CalculatorState,
    onUnitsChanged: (String) -> Unit,
    onPresetSelected: (Double) -> Unit,
    onCalculate: () -> Unit,
    onReset: () -> Unit,
    onLanguageSelected: (AppLanguage) -> Unit,
    onPlanSelected: (com.example.data.tariff.TariffPlan) -> Unit,
    onToggleBreakdownExpanded: () -> Unit,
    onHistoryItemClick: (com.example.domain.model.CalculationHistoryItem) -> Unit,
    onClearHistory: () -> Unit,
    onOpenTariffInfo: () -> Unit,
    onCloseTariffInfo: () -> Unit,
    onOpenSettings: () -> Unit,
    onCloseSettings: () -> Unit,
    onThemeChange: (com.example.domain.model.AppThemeMode) -> Unit,
    onToggleDemandCharge: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .testTag("calculator_screen_scaffold"),
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = androidx.compose.foundation.shape.CircleShape,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(34.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ElectricBolt,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier
                                    .padding(6.dp)
                                    .size(22.dp)
                            )
                        }
                        Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                        Column {
                            Text(
                                text = AppStrings.appTitle(state.language),
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = AppStrings.appSubtitle(state.language),
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.outline
                            )
                        }
                    }
                },
                actions = {
                    IconButton(
                        onClick = onOpenSettings,
                        modifier = Modifier.testTag("settings_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Settings,
                            contentDescription = AppStrings.settingsTitle(state.language),
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 640.dp)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // In-App Language Toggle Bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (state.language == AppLanguage.BANGLA) "ভাষা / Language" else "Language / ভাষা",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.outline
                    )
                    LanguageToggle(
                        currentLanguage = state.language,
                        onLanguageSelected = onLanguageSelected
                    )
                }

                // Tariff category selector (Residential vs Small Commercial)
                TariffSelectorRow(
                    selectedPlan = state.selectedPlan,
                    language = state.language,
                    onPlanSelected = onPlanSelected,
                    onOpenInfo = onOpenTariffInfo
                )

                // Large Unit Input Field (kWh)
                UnitInputField(
                    value = state.unitsInput,
                    onValueChange = onUnitsChanged,
                    language = state.language,
                    errorMessage = state.errorMessage,
                    onCalculate = onCalculate
                )

                // Quick presets row: [50] [100] [200] [300] [500] [1000]
                QuickPresetsRow(
                    language = state.language,
                    onPresetSelected = onPresetSelected
                )

                // Primary Calculate and Reset buttons
                ActionButtonsRow(
                    language = state.language,
                    onCalculate = onCalculate,
                    onReset = onReset
                )

                // Dominant Result Card (Estimated Bill, breakdown, copy, share)
                state.billBreakdown?.let { breakdown ->
                    BillResultCard(
                        breakdown = breakdown,
                        language = state.language
                    )

                    // Slab by slab progressive breakdown card (expandable)
                    SlabBreakdownCard(
                        breakdown = breakdown,
                        isExpanded = state.isBreakdownExpanded,
                        onToggleExpanded = onToggleBreakdownExpanded,
                        language = state.language
                    )
                }

                // Transparent disclaimer card
                DisclaimerCard(language = state.language)

                // AdMob Non-Intrusive Banner (Official Google Test Configuration)
                com.example.ui.calculator.components.BannerAdView(
                    language = state.language
                )

                // Recent Calculations History (last 5 calculations)
                RecentHistoryCard(
                    history = state.history,
                    language = state.language,
                    onItemClick = onHistoryItemClick,
                    onClearHistory = onClearHistory
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }

    // Tariff info bottom sheet
    if (state.showTariffInfoSheet) {
        TariffInfoSheet(
            plan = state.selectedPlan,
            language = state.language,
            onDismiss = onCloseTariffInfo
        )
    }

    // Settings bottom sheet
    if (state.showSettingsSheet) {
        SettingsSheet(
            language = state.language,
            themeMode = state.themeMode,
            includeDemandCharge = state.includeDemandCharge,
            onLanguageChange = onLanguageSelected,
            onThemeChange = onThemeChange,
            onToggleDemandCharge = onToggleDemandCharge,
            onDismiss = onCloseSettings
        )
    }
}
