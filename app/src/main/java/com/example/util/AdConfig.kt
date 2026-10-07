package com.example.util

/**
 * Centralized AdMob advertising configuration for Electricity Bill Calculator BD.
 *
 * Configured with ViveScript Solutions LLC official AdMob credentials:
 * - App ID: ca-app-pub-5222053984568989~6515064111
 * - Banner Ad Unit ID: ca-app-pub-5222053984568989/6167579759
 * - Interstitial Ad Unit ID: ca-app-pub-5222053984568989/9212634784
 */
object AdConfig {
    /**
     * Application ID from Google AdMob console.
     */
    const val APP_ID: String = "ca-app-pub-5222053984568989~6515064111"

    /**
     * Production Banner Ad Unit ID.
     */
    const val BANNER_AD_UNIT_ID: String = "ca-app-pub-5222053984568989/6167579759"

    /**
     * Production Interstitial Ad Unit ID.
     */
    const val INTERSTITIAL_AD_UNIT_ID: String = "ca-app-pub-5222053984568989/9212634784"

    /**
     * Google Official Test Banner Ad Unit ID (fallback for internal test builds if needed).
     */
    const val TEST_BANNER_AD_UNIT_ID: String = "ca-app-pub-3940256099942544/6300978111"

    /**
     * Google Official Test Interstitial Ad Unit ID.
     */
    const val TEST_INTERSTITIAL_AD_UNIT_ID: String = "ca-app-pub-3940256099942544/1033173712"

    /**
     * Flag to switch between live production IDs and Google test IDs.
     * Set to false to use the user's provided live ad units.
     */
    var useTestAds: Boolean = false

    val bannerAdUnitId: String
        get() = if (useTestAds) TEST_BANNER_AD_UNIT_ID else BANNER_AD_UNIT_ID

    val interstitialAdUnitId: String
        get() = if (useTestAds) TEST_INTERSTITIAL_AD_UNIT_ID else INTERSTITIAL_AD_UNIT_ID
}
