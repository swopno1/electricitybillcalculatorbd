package com.example.data.tariff

/**
 * Bangladesh Electricity Tariff Schedules.
 *
 * Source:
 * Bangladesh Energy Regulatory Commission (BERC) & Power Division,
 * Ministry of Power, Energy and Mineral Resources, Government of the People's Republic of Bangladesh.
 * Gazette notification for retail electricity tariff adjustment, effective March 1, 2024.
 *
 * Applicable across Bangladesh utility providers:
 * - DESCO (Dhaka Electric Supply Company Limited)
 * - DPDC (Dhaka Power Distribution Company Limited)
 * - BPDB (Bangladesh Power Development Board)
 * - BREB / PBS (Bangladesh Rural Electrification Board / Palli Bidyut Samity)
 * - NESCO (Northern Electricity Supply Company PLC)
 * - WZPDCL (West Zone Power Distribution Company Limited)
 */
object BangladeshResidentialTariff {
    const val VERSION = "BERC-2024-03"
    const val EFFECTIVE_DATE = "March 1, 2024"
    const val AUTHORITY = "BERC & Power Division, Bangladesh"
    const val GAZETTE_REF = "S.R.O. No. 51-Act/2024 (Retail Electricity Tariff Schedule)"

    /**
     * Lifeline customer threshold (up to 50 units/month).
     * Consumers using 1-50 units pay the subsidised lifeline rate.
     */
    const val LIFELINE_MAX_UNITS = 50.0
    const val LIFELINE_RATE = 4.63 // ৳ 4.63 per unit

    /**
     * Standard residential (LT-A) progressive slabs applicable when consumption exceeds 50 units.
     * Calculated progressively:
     * 1st step: 0 to 75 units @ ৳ 5.26 / unit
     * 2nd step: 76 to 200 units @ ৳ 7.20 / unit
     * 3rd step: 201 to 300 units @ ৳ 7.59 / unit
     * 4th step: 301 to 400 units @ ৳ 8.02 / unit
     * 5th step: 401 to 600 units @ ৳ 12.67 / unit
     * 6th step: Above 600 units @ ৳ 14.61 / unit
     */
    val slabs = listOf(
        TariffSlab(
            slabIndex = 1,
            fromUnit = 0.0,
            toUnit = 75.0,
            ratePerUnit = 5.26,
            nameEn = "Slab 1 (0 – 75 units)",
            nameBn = "১ম ধাপ (০ – ৭৫ ইউনিট)"
        ),
        TariffSlab(
            slabIndex = 2,
            fromUnit = 75.0,
            toUnit = 200.0,
            ratePerUnit = 7.20,
            nameEn = "Slab 2 (76 – 200 units)",
            nameBn = "২য় ধাপ (৭৬ – ২০০ ইউনিট)"
        ),
        TariffSlab(
            slabIndex = 3,
            fromUnit = 200.0,
            toUnit = 300.0,
            ratePerUnit = 7.59,
            nameEn = "Slab 3 (201 – 300 units)",
            nameBn = "৩য় ধাপ (২০১ – ৩০০ ইউনিট)"
        ),
        TariffSlab(
            slabIndex = 4,
            fromUnit = 300.0,
            toUnit = 400.0,
            ratePerUnit = 8.02,
            nameEn = "Slab 4 (301 – 400 units)",
            nameBn = "৪র্থ ধাপ (৩০১ – ৪০০ ইউনিট)"
        ),
        TariffSlab(
            slabIndex = 5,
            fromUnit = 400.0,
            toUnit = 600.0,
            ratePerUnit = 12.67,
            nameEn = "Slab 5 (401 – 600 units)",
            nameBn = "৫ম ধাপ (৪০১ – ৬০০ ইউনিট)"
        ),
        TariffSlab(
            slabIndex = 6,
            fromUnit = 600.0,
            toUnit = null,
            ratePerUnit = 14.61,
            nameEn = "Slab 6 (Above 600 units)",
            nameBn = "৬ষ্ঠ ধাপ (৬০০ ইউনিটের ঊর্ধ্বে)"
        )
    )

    /**
     * Residential monthly demand charge per sanctioned kilowatt (kW).
     * Single-phase connection typically 1 kW - 2 kW.
     */
    const val RESIDENTIAL_DEMAND_CHARGE_PER_KW = 42.0

    /**
     * Value Added Tax (VAT) rate on retail electricity consumption.
     */
    const val VAT_PERCENTAGE = 5.0

    val plan = TariffPlan(
        id = "bd_residential_2024",
        nameEn = "Residential (LT-A)",
        nameBn = "আবাসিক (এলটি-এ)",
        category = TariffCategory.RESIDENTIAL,
        authority = AUTHORITY,
        gazetteNotice = GAZETTE_REF,
        effectiveDate = EFFECTIVE_DATE,
        isLifelineSupported = true,
        lifelineMaxUnits = LIFELINE_MAX_UNITS,
        lifelineRate = LIFELINE_RATE,
        slabs = slabs,
        defaultDemandChargePerKw = RESIDENTIAL_DEMAND_CHARGE_PER_KW,
        vatPercentage = VAT_PERCENTAGE,
        notesEn = "Progressive slab pricing based on BERC March 2024 retail schedule. Up to 50 units benefits from the subsidized lifeline rate.",
        notesBn = "বিইআরসি মার্চ ২০২৪ নির্ধারিত ধাপভিত্তিক মূল্য। ৫০ ইউনিট পর্যন্ত বিশেষ লাইফলাইন রেট প্রযোজ্য।"
    )
}

/**
 * Bangladesh Small Commercial (LT-E) Electricity Tariff.
 * Official BERC March 2024 retail tariff for small shops and commercial establishments.
 */
object BangladeshSmallCommercialTariff {
    const val VERSION = "BERC-2024-03"
    const val EFFECTIVE_DATE = "March 1, 2024"
    const val FLAT_RATE = 13.01 // ৳ 13.01 per unit flat rate
    const val DEMAND_CHARGE_PER_KW = 84.0 // ৳ 84 per kW for commercial LT-E

    val slabs = listOf(
        TariffSlab(
            slabIndex = 1,
            fromUnit = 0.0,
            toUnit = null,
            ratePerUnit = FLAT_RATE,
            nameEn = "Flat Rate (All units)",
            nameBn = "ফ্ল্যাট রেট (সব ইউনিট)"
        )
    )

    val plan = TariffPlan(
        id = "bd_commercial_2024",
        nameEn = "Small Commercial (LT-E)",
        nameBn = "ক্ষুদ্র বাণিজ্যিক (এলটি-ই)",
        category = TariffCategory.SMALL_COMMERCIAL,
        authority = BangladeshResidentialTariff.AUTHORITY,
        gazetteNotice = BangladeshResidentialTariff.GAZETTE_REF,
        effectiveDate = EFFECTIVE_DATE,
        isLifelineSupported = false,
        lifelineMaxUnits = 0.0,
        lifelineRate = 0.0,
        slabs = slabs,
        defaultDemandChargePerKw = DEMAND_CHARGE_PER_KW,
        vatPercentage = 5.0,
        notesEn = "Flat commercial tariff rate of ৳ 13.01/unit plus demand charge and 5% VAT.",
        notesBn = "বাণিজ্যিক ফ্ল্যাট রেট প্রতি ইউনিট ১৩.০১ টাকা, সাথে ডিমান্ড চার্জ ও ৫% ভ্যাট।"
    )
}
