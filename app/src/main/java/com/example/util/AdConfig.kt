package com.example.util

/**
 * Centralized AdMob advertising configuration.
 *
 * Current Mode: Official Google Test configuration.
 *
 * Production Migration Steps for ViveScript Solutions LLC:
 * 1. Obtain production App ID and Ad Unit IDs from your Google AdMob Console.
 * 2. Update AndroidManifest.xml `com.google.android.gms.ads.APPLICATION_ID` with the production App ID.
 * 3. Set [PRODUCTION_BANNER_AD_UNIT_ID] to your production banner ad unit ID.
 * 4. Toggle [IS_TEST_MODE] to false before generating release AAB/APK for Google Play.
 */
object AdConfig {
    /**
     * Controls whether test ads or production ads are requested.
     * Keep true during development and testing to prevent policy violations.
     */
    const val IS_TEST_MODE: Boolean = true

    /**
     * Official Google Test App ID.
     */
    const val TEST_APP_ID: String = "ca-app-pub-3940256099942544~3347511713"

    /**
     * Official Google Test Banner Ad Unit ID.
     */
    const val TEST_BANNER_AD_UNIT_ID: String = "ca-app-pub-3940256099942544/6300978111"

    /**
     * Production Banner Ad Unit ID (replace with production ID for Play Store release).
     */
    const val PRODUCTION_BANNER_AD_UNIT_ID: String = ""

    /**
     * Active banner ad unit ID based on mode.
     */
    val bannerAdUnitId: String
        get() = if (IS_TEST_MODE || PRODUCTION_BANNER_AD_UNIT_ID.isBlank()) {
            TEST_BANNER_AD_UNIT_ID
        } else {
            PRODUCTION_BANNER_AD_UNIT_ID
        }
}
