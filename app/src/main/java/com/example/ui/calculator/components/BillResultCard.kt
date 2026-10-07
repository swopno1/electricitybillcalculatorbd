package com.example.ui.calculator.components

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.calculator.ElectricityBillCalculator
import com.example.domain.model.AppLanguage
import com.example.domain.model.BillBreakdown
import com.example.ui.util.AppStrings

@Composable
fun BillResultCard(
    breakdown: BillBreakdown,
    language: AppLanguage,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current

    val totalFormatted = ElectricityBillCalculator.formatCurrency(
        amount = breakdown.totalAmount,
        language = language,
        includeDecimals = true
    )
    val energyFormatted = ElectricityBillCalculator.formatCurrency(
        amount = breakdown.energyCharge,
        language = language,
        includeDecimals = true
    )
    val demandFormatted = ElectricityBillCalculator.formatCurrency(
        amount = breakdown.demandCharge,
        language = language,
        includeDecimals = true
    )
    val vatFormatted = ElectricityBillCalculator.formatCurrency(
        amount = breakdown.vatAmount,
        language = language,
        includeDecimals = true
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .testTag("bill_result_card"),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            // Header: Category badge & Lifeline indicator
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = AppStrings.estimatedBillTitle(language),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                if (breakdown.isLifeline) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ) {
                        Text(
                            text = if (language == AppLanguage.BANGLA) "লাইফলাইন রেট" else "Lifeline Rate",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Visually Dominant Amount
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(vertical = 16.dp, horizontal = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = totalFormatted,
                        fontSize = 38.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.testTag("estimated_total_display")
                    )

                    val unitsDisplay = if (language == AppLanguage.BANGLA) {
                        "${ElectricityBillCalculator.toBengaliDigits(ElectricityBillCalculator.formatNumber(breakdown.units, language))} ইউনিট"
                    } else {
                        "${ElectricityBillCalculator.formatNumber(breakdown.units, language)} units (kWh)"
                    }
                    Text(
                        text = unitsDisplay,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Compact breakdown table
            BreakdownRow(
                label = AppStrings.energyChargeLabel(language),
                value = energyFormatted
            )
            BreakdownRow(
                label = AppStrings.demandChargeLabel(language),
                value = demandFormatted
            )
            BreakdownRow(
                label = AppStrings.vatLabel(language, breakdown.vatPercentage),
                value = vatFormatted
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 10.dp),
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
            )

            BreakdownRow(
                label = AppStrings.totalLabel(language),
                value = totalFormatted,
                isTotal = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Action Buttons: Copy & Share
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                OutlinedButton(
                    onClick = {
                        val shareText = generateShareText(breakdown, language)
                        clipboardManager.setText(AnnotatedString(shareText))
                        Toast.makeText(context, AppStrings.copiedToast(language), Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .weight(1f)
                        .testTag("copy_result_btn"),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ContentCopy,
                        contentDescription = null,
                        modifier = Modifier.padding(end = 6.dp)
                    )
                    Text(text = AppStrings.copyResult(language), fontWeight = FontWeight.SemiBold)
                }

                OutlinedButton(
                    onClick = {
                        val shareText = generateShareText(breakdown, language)
                        shareBillResult(context, shareText)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .testTag("share_result_btn"),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = null,
                        modifier = Modifier.padding(end = 6.dp)
                    )
                    Text(text = AppStrings.shareResult(language), fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

@Composable
private fun BreakdownRow(
    label: String,
    value: String,
    isTotal: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = if (isTotal) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyMedium,
            fontWeight = if (isTotal) FontWeight.Bold else FontWeight.Normal,
            color = if (isTotal) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = if (isTotal) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyMedium,
            fontWeight = if (isTotal) FontWeight.Bold else FontWeight.SemiBold,
            color = if (isTotal) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        )
    }
}

private fun generateShareText(breakdown: BillBreakdown, language: AppLanguage): String {
    val totalStr = ElectricityBillCalculator.formatCurrency(breakdown.totalAmount, language)
    val energyStr = ElectricityBillCalculator.formatCurrency(breakdown.energyCharge, language)
    val demandStr = ElectricityBillCalculator.formatCurrency(breakdown.demandCharge, language)
    val vatStr = ElectricityBillCalculator.formatCurrency(breakdown.vatAmount, language)

    return if (language == AppLanguage.BANGLA) {
        val unitsBn = ElectricityBillCalculator.toBengaliDigits(
            ElectricityBillCalculator.formatNumber(breakdown.units, language)
        )
        """
        বিদ্যুৎ বিল ক্যালকুলেটর BD
        
        মাসিক ব্যবহার: $unitsBn ইউনিট
        আনুমানিক বিল: $totalStr
        
        বিস্তারিত হিসাব:
        • বিদ্যুৎ মূল্য: $energyStr
        • ডিমান্ড চার্জ: $demandStr
        • ভ্যাট (${breakdown.vatPercentage.toInt()}%): $vatStr
        মোট: $totalStr
        
        ট্যারিফ: ${breakdown.planNameBn} (${breakdown.effectiveDate})
        এটি একটি আনুমানিক হিসাব। প্রকৃত বিল কিছুটা ভিন্ন হতে পারে।
        """.trimIndent()
    } else {
        val unitsEn = ElectricityBillCalculator.formatNumber(breakdown.units, language)
        """
        Electricity Bill Calculator BD
        
        Monthly usage: $unitsEn units (kWh)
        Estimated bill: $totalStr
        
        Breakdown:
        • Energy charge: $energyStr
        • Demand charge: $demandStr
        • VAT (${breakdown.vatPercentage.toInt()}%): $vatStr
        Estimated Total: $totalStr
        
        Tariff: ${breakdown.planNameEn} (${breakdown.effectiveDate})
        This is an estimated calculation and may differ from your actual electricity bill.
        """.trimIndent()
    }
}

private fun shareBillResult(context: Context, text: String) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, "Share Electricity Bill Estimate")
    context.startActivity(shareIntent)
}
