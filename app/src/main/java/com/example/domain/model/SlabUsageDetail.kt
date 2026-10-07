package com.example.domain.model

import java.math.BigDecimal

/**
 * Breakdown of consumption and charge for an individual tariff slab.
 */
data class SlabUsageDetail(
    val slabIndex: Int,
    val slabNameEn: String,
    val slabNameBn: String,
    val unitRangeEn: String,
    val unitRangeBn: String,
    val unitsInSlab: Double,
    val ratePerUnit: Double,
    val amount: BigDecimal
)
