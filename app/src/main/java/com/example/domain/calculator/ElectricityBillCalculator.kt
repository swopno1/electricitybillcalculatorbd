package com.example.domain.calculator

import com.example.data.tariff.TariffPlan
import com.example.domain.model.AppLanguage
import com.example.domain.model.BillBreakdown
import com.example.domain.model.SlabUsageDetail
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

object ElectricityBillCalculator {

    /**
     * Calculates the estimated electricity bill progressively based on Bangladesh tariff schedule.
     *
     * @param units Total electricity usage in kilowatt-hours (kWh)
     * @param plan The applicable tariff plan (defaulting to Bangladesh Residential)
     * @param sanctionedLoadKw Sanctioned electrical load in kW (typically 1.0 or 2.0 kW)
     * @param includeDemandCharge Whether to include monthly demand charge
     */
    fun calculate(
        units: Double,
        plan: TariffPlan,
        sanctionedLoadKw: Double = 1.0,
        includeDemandCharge: Boolean = true
    ): BillBreakdown {
        require(units >= 0.0) { "Usage units cannot be negative" }

        val slabDetails = mutableListOf<SlabUsageDetail>()
        var totalEnergyCharge = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP)

        // Check if Lifeline applies (residential only, <= 50 units)
        val isLifeline = plan.isLifelineSupported && units > 0.0 && units <= plan.lifelineMaxUnits

        if (units == 0.0) {
            // Zero units consumed
            slabDetails.add(
                SlabUsageDetail(
                    slabIndex = 1,
                    slabNameEn = "Zero usage",
                    slabNameBn = "ব্যবহার নেই",
                    unitRangeEn = "0 kWh",
                    unitRangeBn = "০ kWh",
                    unitsInSlab = 0.0,
                    ratePerUnit = 0.0,
                    amount = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP)
                )
            )
        } else if (isLifeline) {
            // Lifeline slab: all units charged at lifeline rate
            val rate = plan.lifelineRate
            val amount = BigDecimal.valueOf(units)
                .multiply(BigDecimal.valueOf(rate))
                .setScale(2, RoundingMode.HALF_UP)

            slabDetails.add(
                SlabUsageDetail(
                    slabIndex = 0,
                    slabNameEn = "Lifeline (0 – ${plan.lifelineMaxUnits.toInt()} units)",
                    slabNameBn = "লাইফলাইন (০ – ${toBengaliDigits(plan.lifelineMaxUnits.toInt().toString())} ইউনিট)",
                    unitRangeEn = "0 – ${plan.lifelineMaxUnits.toInt()} kWh",
                    unitRangeBn = "০ – ${toBengaliDigits(plan.lifelineMaxUnits.toInt().toString())} kWh",
                    unitsInSlab = units,
                    ratePerUnit = rate,
                    amount = amount
                )
            )
            totalEnergyCharge = amount
        } else {
            // Standard progressive slab calculation
            var remainingUnits = units

            for (slab in plan.slabs) {
                if (remainingUnits <= 0.0) break

                val slabCapacity = slab.capacity()
                val unitsInThisSlab: Double = if (slabCapacity != null) {
                    minOf(remainingUnits, slabCapacity)
                } else {
                    // Open-ended slab (e.g. above 600 units)
                    remainingUnits
                }

                if (unitsInThisSlab > 0.0) {
                    val slabAmount = BigDecimal.valueOf(unitsInThisSlab)
                        .multiply(BigDecimal.valueOf(slab.ratePerUnit))
                        .setScale(2, RoundingMode.HALF_UP)

                    val rangeEn = if (slab.toUnit != null) {
                        "${slab.fromUnit.toInt()} – ${slab.toUnit.toInt()} kWh"
                    } else {
                        "> ${slab.fromUnit.toInt()} kWh"
                    }

                    val rangeBn = if (slab.toUnit != null) {
                        "${toBengaliDigits(slab.fromUnit.toInt().toString())} – ${toBengaliDigits(slab.toUnit.toInt().toString())} kWh"
                    } else {
                        "> ${toBengaliDigits(slab.fromUnit.toInt().toString())} kWh"
                    }

                    slabDetails.add(
                        SlabUsageDetail(
                            slabIndex = slab.slabIndex,
                            slabNameEn = slab.nameEn,
                            slabNameBn = slab.nameBn,
                            unitRangeEn = rangeEn,
                            unitRangeBn = rangeBn,
                            unitsInSlab = unitsInThisSlab,
                            ratePerUnit = slab.ratePerUnit,
                            amount = slabAmount
                        )
                    )

                    totalEnergyCharge = totalEnergyCharge.add(slabAmount)
                    remainingUnits -= unitsInThisSlab
                }
            }
        }

        // Demand charge calculation
        val demandCharge = if (includeDemandCharge && sanctionedLoadKw > 0.0) {
            BigDecimal.valueOf(sanctionedLoadKw)
                .multiply(BigDecimal.valueOf(plan.defaultDemandChargePerKw))
                .setScale(2, RoundingMode.HALF_UP)
        } else {
            BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP)
        }

        // VAT calculation (5% on Energy Charge + Demand Charge)
        val taxableSubtotal = totalEnergyCharge.add(demandCharge)
        val vatAmount = taxableSubtotal
            .multiply(BigDecimal.valueOf(plan.vatPercentage / 100.0))
            .setScale(2, RoundingMode.HALF_UP)

        // Total calculation
        val totalAmount = taxableSubtotal.add(vatAmount).setScale(2, RoundingMode.HALF_UP)
        val totalRounded = totalAmount.setScale(0, RoundingMode.HALF_UP).toLong()

        return BillBreakdown(
            units = units,
            planId = plan.id,
            planNameEn = plan.nameEn,
            planNameBn = plan.nameBn,
            isLifeline = isLifeline,
            slabDetails = slabDetails,
            energyCharge = totalEnergyCharge,
            sanctionedLoadKw = if (includeDemandCharge) sanctionedLoadKw else 0.0,
            demandCharge = demandCharge,
            vatPercentage = plan.vatPercentage,
            vatAmount = vatAmount,
            otherCharges = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP),
            totalAmount = totalAmount,
            totalRounded = totalRounded,
            effectiveDate = plan.effectiveDate,
            authority = plan.authority
        )
    }

    /**
     * Converts western digits 0-9 to Bengali digits ০-৯.
     */
    fun toBengaliDigits(input: String): String {
        val bengaliDigits = charArrayOf('০', '১', '২', '৩', '৪', '৫', '৬', '৭', '৮', '৯')
        val sb = StringBuilder()
        for (char in input) {
            if (char in '0'..'9') {
                sb.append(bengaliDigits[char - '0'])
            } else {
                sb.append(char)
            }
        }
        return sb.toString()
    }

    /**
     * Formats currency amounts in Taka (৳).
     * If showDecimals is false or value is a round number, formats without decimal places.
     */
    fun formatCurrency(
        amount: BigDecimal,
        language: AppLanguage,
        includeDecimals: Boolean = true
    ): String {
        val isWholeNumber = amount.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0
        val pattern = if (includeDecimals && !isWholeNumber) "#,##0.00" else "#,##0"
        val symbols = DecimalFormatSymbols(Locale.US)
        val formatter = DecimalFormat(pattern, symbols)
        val formatted = formatter.format(amount)

        return if (language == AppLanguage.BANGLA) {
            "৳ ${toBengaliDigits(formatted)}"
        } else {
            "৳ $formatted"
        }
    }

    fun formatNumber(
        value: Double,
        language: AppLanguage,
        maxDecimals: Int = 2
    ): String {
        val isWhole = value % 1.0 == 0.0
        val pattern = if (isWhole) "#,##0" else "#,##0.${"#".repeat(maxDecimals)}"
        val formatter = DecimalFormat(pattern, DecimalFormatSymbols(Locale.US))
        val formatted = formatter.format(value)
        return if (language == AppLanguage.BANGLA) {
            toBengaliDigits(formatted)
        } else {
            formatted
        }
    }
}
