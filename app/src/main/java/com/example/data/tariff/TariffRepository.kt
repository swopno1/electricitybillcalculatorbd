package com.example.data.tariff

/**
 * Repository providing tariff plans.
 * Extensible for adding specific distribution companies (DESCO, DPDC, BPDB, BREB, NESCO, WZPDCL).
 */
class TariffRepository {
    fun getAllPlans(): List<TariffPlan> = listOf(
        BangladeshResidentialTariff.plan,
        BangladeshSmallCommercialTariff.plan
    )

    fun getDefaultPlan(): TariffPlan = BangladeshResidentialTariff.plan

    fun getPlanById(id: String): TariffPlan {
        return getAllPlans().firstOrNull { it.id == id } ?: getDefaultPlan()
    }

    fun getSupportedProviders(): List<String> = listOf(
        "DESCO (Dhaka Electric Supply Company)",
        "DPDC (Dhaka Power Distribution Company)",
        "BPDB (Bangladesh Power Development Board)",
        "BREB / Palli Bidyut (Rural Electrification Board)",
        "NESCO (Northern Electricity Supply Company)",
        "WZPDCL (West Zone Power Distribution Company)"
    )
}
