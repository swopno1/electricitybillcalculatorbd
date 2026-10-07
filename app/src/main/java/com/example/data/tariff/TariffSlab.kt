package com.example.data.tariff

/**
 * Represents a progressive electricity tariff slab in Bangladesh.
 *
 * @param slabIndex 1-based slab sequence number (0 for lifeline)
 * @param fromUnit Starting units (exclusive of lower bound, inclusive of start range)
 * @param toUnit Upper limit of units in this slab, or null if open-ended (e.g. > 600)
 * @param ratePerUnit Rate per kWh in Bangladesh Taka (৳)
 * @param nameEn Descriptive label in English
 * @param nameBn Descriptive label in Bangla
 */
data class TariffSlab(
    val slabIndex: Int,
    val fromUnit: Double,
    val toUnit: Double?,
    val ratePerUnit: Double,
    val nameEn: String,
    val nameBn: String
) {
    /**
     * Checks if a consumption amount falls into or reaches this slab.
     */
    fun capacity(): Double? = toUnit?.let { it - fromUnit }
}
