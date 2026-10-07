package com.example.ui.sheets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.AppLanguage
import com.example.domain.model.AppThemeMode
import com.example.ui.util.AppStrings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsSheet(
    language: AppLanguage,
    themeMode: AppThemeMode,
    includeDemandCharge: Boolean,
    onLanguageChange: (AppLanguage) -> Unit,
    onThemeChange: (AppThemeMode) -> Unit,
    onToggleDemandCharge: (Boolean) -> Unit,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        modifier = Modifier.testTag("settings_bottom_sheet")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 12.dp)
        ) {
            Text(
                text = AppStrings.settingsTitle(language),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Language setting
            Text(
                text = AppStrings.languageLabel(language),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FilterChip(
                    selected = language == AppLanguage.BANGLA,
                    onClick = { onLanguageChange(AppLanguage.BANGLA) },
                    label = { Text("বাংলা") },
                    modifier = Modifier.testTag("settings_lang_bn")
                )
                FilterChip(
                    selected = language == AppLanguage.ENGLISH,
                    onClick = { onLanguageChange(AppLanguage.ENGLISH) },
                    label = { Text("English") },
                    modifier = Modifier.testTag("settings_lang_en")
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Theme setting
            Text(
                text = AppStrings.themeLabel(language),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                FilterChip(
                    selected = themeMode == AppThemeMode.SYSTEM,
                    onClick = { onThemeChange(AppThemeMode.SYSTEM) },
                    label = { Text(AppStrings.themeSystem(language)) },
                    modifier = Modifier.testTag("settings_theme_system")
                )
                FilterChip(
                    selected = themeMode == AppThemeMode.LIGHT,
                    onClick = { onThemeChange(AppThemeMode.LIGHT) },
                    label = { Text(AppStrings.themeLight(language)) },
                    modifier = Modifier.testTag("settings_theme_light")
                )
                FilterChip(
                    selected = themeMode == AppThemeMode.DARK,
                    onClick = { onThemeChange(AppThemeMode.DARK) },
                    label = { Text(AppStrings.themeDark(language)) },
                    modifier = Modifier.testTag("settings_theme_dark")
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Demand charge option
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = AppStrings.demandChargeToggle(language),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = if (language == AppLanguage.BANGLA) "আবাসিক সংযোগে সাধারণত ন্যূনতম ১ kW ডিমান্ড চার্জ (৳ ৪২) যোগ হয়" else "Standard residential connections incur 1 kW demand charge (৳ 42)",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
                Switch(
                    checked = includeDemandCharge,
                    onCheckedChange = onToggleDemandCharge,
                    modifier = Modifier.testTag("demand_charge_switch")
                )
            }

            Spacer(modifier = Modifier.height(18.dp))
            HorizontalDivider()
            Spacer(modifier = Modifier.height(14.dp))

            // About Section
            Text(
                text = AppStrings.aboutTitle(language),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = AppStrings.aboutDescription(language),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                lineHeight = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = AppStrings.developedBy(language),
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = AppStrings.websiteUrl(),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = AppStrings.copyrightNotice(),
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = AppStrings.sourceCodeLicense(),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.outline
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = AppStrings.brandingNotice(language),
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.outline,
                lineHeight = 15.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onDismiss,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("close_settings_btn"),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = AppStrings.close(language))
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
