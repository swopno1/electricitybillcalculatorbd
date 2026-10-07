package com.example.util

import android.app.Activity
import android.content.Context
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

/**
 * Manages loading and displaying Google AdMob Interstitial Ads in compliance with AdMob policies.
 *
 * AdMob Policy Compliance Rules:
 * - Does not show ads unexpectedly or immediately on launch.
 * - Implements a 60-second cooldown interval and action threshold (every 3rd significant action).
 * - Preloads the next ad upon dismissal or failure.
 * - Never blocks the user's workflow if ad is not loaded or fails.
 */
object InterstitialAdManager {
    private var interstitialAd: InterstitialAd? = null
    private var isLoading: Boolean = false
    private var lastShownTime: Long = 0L
    private var actionCounter: Int = 0

    private const val MIN_INTERVAL_MS = 60_000L
    private const val ACTION_THRESHOLD = 3

    fun preload(context: Context) {
        if (interstitialAd != null || isLoading) return
        isLoading = true

        try {
            val adRequest = AdRequest.Builder().build()
            InterstitialAd.load(
                context.applicationContext,
                AdConfig.interstitialAdUnitId,
                adRequest,
                object : InterstitialAdLoadCallback() {
                    override fun onAdLoaded(ad: InterstitialAd) {
                        interstitialAd = ad
                        isLoading = false
                    }

                    override fun onAdFailedToLoad(error: LoadAdError) {
                        interstitialAd = null
                        isLoading = false
                    }
                }
            )
        } catch (_: Exception) {
            interstitialAd = null
            isLoading = false
        }
    }

    /**
     * Checks eligibility based on cooldown and frequency capping, then shows the ad.
     * Calls [onComplete] immediately if ineligible or ad not ready, ensuring zero disruption.
     */
    fun showIfEligible(activity: Activity, onComplete: () -> Unit = {}) {
        actionCounter++
        val now = System.currentTimeMillis()
        val ad = interstitialAd

        if (actionCounter >= ACTION_THRESHOLD && (now - lastShownTime) >= MIN_INTERVAL_MS && ad != null) {
            try {
                ad.fullScreenContentCallback = object : FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        interstitialAd = null
                        lastShownTime = System.currentTimeMillis()
                        actionCounter = 0
                        preload(activity)
                        onComplete()
                    }

                    override fun onAdFailedToShowFullScreenContent(error: AdError) {
                        interstitialAd = null
                        preload(activity)
                        onComplete()
                    }
                }
                ad.show(activity)
            } catch (_: Exception) {
                interstitialAd = null
                onComplete()
            }
        } else {
            if (interstitialAd == null && !isLoading) {
                preload(activity)
            }
            onComplete()
        }
    }
}
