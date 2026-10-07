package com.example.ui.sheets

import androidx.compose.foundation.background
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.tariff.BangladeshResidentialTariff
import com.example.data.tariff.TariffPlan
import com.example.domain.calculator.ElectricityBillCalculator
import com.example.domain.model.AppLanguage
import com.example.ui.util.AppStrings

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TariffInfoSheet(
    plan: TariffPlan,
    language: AppLanguage,
    onDismiss: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        modifier = Modifier.testTag("tariff_info_bottom_sheet")
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 12.dp)
        ) {
            Text(
                text = AppStrings.tariffInfoTitle(language),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = if (language == AppLanguage.BANGLA) plan.nameBn else plan.nameEn,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Metadata Card
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp)),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    InfoLine(
                        label = if (language == AppLanguage.BANGLA) "কর্তৃপক্ষ:" else "Authority:",
                        value = plan.authority
                    )
                    InfoLine(
                        label = if (language == AppLanguage.BANGLA) "কার্যকর তারিখ:" else "Effective Date:",
                        value = plan.effectiveDate
                    )
                    InfoLine(
                        label = if (language == AppLanguage.BANGLA) "গেজেট রেফারেন্স:" else "Gazette Reference:",
                        value = plan.gazetteNotice
                    )
                    InfoLine(
                        label = if (language == AppLanguage.BANGLA) "ডিমান্ড চার্জ:" else "Demand Charge:",
                        value = if (language == AppLanguage.BANGLA) "৳ ৪২.০০ / kW / মাস" else "৳ 42.00 / kW / month"
                    )
                    InfoLine(
                        label = if (language == AppLanguage.BANGLA) "সরকারি ভ্যাট:" else "Government VAT:",
                        value = if (language == AppLanguage.BANGLA) "৫%" else "5%"
                    )
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = if (language == AppLanguage.BANGLA) "ধাপভিত্তিক বিদ্যুতের মূল্য তালিকা" else "Slab Rate Schedule",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Lifeline row
            if (plan.isLifelineSupported) {
                SlabRateRow(
                    name = if (language == AppLanguage.BANGLA) "লাইফলাইন (১ – ৫০ ইউনিট)" else "Lifeline (1 – 50 units)",
                    rate = if (language == AppLanguage.BANGLA) "৳ ৪.৬৩" else "৳ 4.63",
                    isLifeline = true
                )
            }

            // Normal slabs
            plan.slabs.forEach { slab ->
                val name = if (language == AppLanguage.BANGLA) slab.nameBn else slab.nameEn
                val rate = if (language == AppLanguage.BANGLA) {
                    "৳ ${ElectricityBillCalculator.toBengaliDigits(String.format("%.2f", slab.ratePerUnit))}"
                } else {
                    "৳ ${String.format("%.2f", slab.ratePerUnit)}"
                }
                SlabRateRow(name = name, rate = rate, isLifeline = false)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Applicable utilities notice
            Text(
                text = if (language == AppLanguage.BANGLA) {
                    "প্রযোজ্য বিদ্যুৎ বিতরণকারী সংস্থাসমূহ:\nDESCO, DPDC, BPDB, BREB (পল্লী বিদ্যুৎ), NESCO, WZPDCL。"
                } else {
                    "Applicable Electricity Distribution Companies:\nDESCO, DPDC, BPDB, BREB (Palli Bidyut), NESCO, WZPDCL."
                },
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(12.dp))

            HorizontalDivider()

            Spacer(modifier = Modifier.height(12.dp))

            // Disclaimer
            Text(
                text = if (language == AppLanguage.BANGLA) {
                    "সতর্কবার্তা: এটি একটি স্বাধীন হিসাব সহায়ক অ্যাপ্লিকেশন। এই অ্যাপটি বাংলাদেশ সরকার বা কোনো বিদ্যুৎ বিতরণ কর্তৃপক্ষের প্রাতিষ্ঠানিক অ্যাপ নয়। প্রকৃত বিলিং সিস্টেমে মিটার ভাড়া, বিলম্ব মাশুল বা বিশেষ সমন্বয় থাকতে পারে।"
                } else {
                    "Disclaimer: This is an independent calculation tool and is NOT affiliated with or endorsed by BPDB, DESCO, DPDC, BREB, NESCO, WZPDCL, or the Bangladesh Government."
                },
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.outline
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = onDismiss,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("close_tariff_info_btn"),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = AppStrings.close(language))
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun InfoLine(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
private fun SlabRateRow(
    name: String,
    rate: String,
    isLifeline: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(
                if (isLifeline) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
                else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.25f)
            )
            .padding(horizontal = 12.dp, vertical = 9.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = if (isLifeline) FontWeight.Bold else FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = "$rate / kWh",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
