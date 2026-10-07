package com.example.domain.model

import java.math.BigDecimal

/**
 * Transparent breakdown of an electricity bill calculation in Bangladesh.
 */
data class BillBreakdown(
    val units: Double,
    val planId: String,
    val planNameEn: String,
    val planNameBn: String,
    val isLifeline: Boolean,
    val slabDetails: List<SlabUsageDetail>,
    val energyCharge: BigDecimal,
    val sanctionedLoadKw: Double,
    val demandCharge: BigDecimal,
    val vatPercentage: Double,
    val vatAmount: BigDecimal,
    val otherCharges: BigDecimal = BigDecimal.ZERO,
    val totalAmount: BigDecimal,
    val totalRounded: Long,
    val effectiveDate: String,
    val authority: String
)
