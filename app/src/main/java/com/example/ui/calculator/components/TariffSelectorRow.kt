package com.example.ui.calculator.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.tariff.BangladeshResidentialTariff
import com.example.data.tariff.BangladeshSmallCommercialTariff
import com.example.data.tariff.TariffCategory
import com.example.data.tariff.TariffPlan
import com.example.domain.model.AppLanguage
import com.example.ui.util.AppStrings

@Composable
fun TariffSelectorRow(
    selectedPlan: TariffPlan,
    language: AppLanguage,
    onPlanSelected: (TariffPlan) -> Unit,
    onOpenInfo: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Residential Chip
        val isResidential = selectedPlan.category == TariffCategory.RESIDENTIAL
        FilterChip(
            selected = isResidential,
            onClick = { onPlanSelected(BangladeshResidentialTariff.plan) },
            label = {
                Text(
                    text = AppStrings.residential(language),
                    fontWeight = if (isResidential) FontWeight.Bold else FontWeight.Normal
                )
            },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            modifier = Modifier.testTag("tariff_chip_residential")
        )

        // Commercial Chip
        val isCommercial = selectedPlan.category == TariffCategory.SMALL_COMMERCIAL
        FilterChip(
            selected = isCommercial,
            onClick = { onPlanSelected(BangladeshSmallCommercialTariff.plan) },
            label = {
                Text(
                    text = AppStrings.smallCommercial(language),
                    fontWeight = if (isCommercial) FontWeight.Bold else FontWeight.Normal
                )
            },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            modifier = Modifier.testTag("tariff_chip_commercial")
        )

        // Info Button to view full tariff rates
        IconButton(
            onClick = onOpenInfo,
            modifier = Modifier
                .testTag("tariff_info_btn")
                .size(40.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = AppStrings.tariffInfoTitle(language),
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}
