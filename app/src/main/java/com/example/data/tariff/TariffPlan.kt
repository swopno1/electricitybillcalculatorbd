package com.example.data.tariff

enum class TariffCategory {
    RESIDENTIAL,
    SMALL_COMMERCIAL
}

/**
 * Encapsulates an electricity tariff plan for Bangladesh.
 */
data class TariffPlan(
    val id: String,
    val nameEn: String,
    val nameBn: String,
    val category: TariffCategory,
    val authority: String,
    val gazetteNotice: String,
    val effectiveDate: String,
    val isLifelineSupported: Boolean,
    val lifelineMaxUnits: Double,
    val lifelineRate: Double,
    val slabs: List<TariffSlab>,
    val defaultDemandChargePerKw: Double,
    val vatPercentage: Double = 5.0,
    val notesEn: String,
    val notesBn: String
)
