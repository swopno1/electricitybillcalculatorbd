package com.example.ui.calculator.components

import android.view.View
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.domain.model.AppLanguage
import com.example.util.AdConfig
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError

@Composable
fun BannerAdView(
    language: AppLanguage,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .testTag("admob_banner_container"),
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = if (language == AppLanguage.BANGLA) "বিজ্ঞাপন" else "SPONSORED",
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.outline,
                letterSpacing = 1.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                contentAlignment = Alignment.Center
            ) {
                AndroidView(
                    factory = { ctx ->
                        AdView(ctx).apply {
                            setAdSize(AdSize.BANNER)
                            adUnitId = AdConfig.bannerAdUnitId

                            // Use software layer on virtualized rendering environments
                            // to prevent MESA rendernode missing errors
                            try {
                                setLayerType(View.LAYER_TYPE_SOFTWARE, null)
                            } catch (_: Exception) {
                            }

                            adListener = object : AdListener() {
                                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                                    // Handle load failures gracefully without disrupting UI
                                }
                            }

                            try {
                                val adRequest = AdRequest.Builder().build()
                                loadAd(adRequest)
                            } catch (_: Exception) {
                            }
                        }
                    },
                    onRelease = { adView ->
                        try {
                            adView.destroy()
                        } catch (_: Exception) {
                        }
                    },
                    modifier = Modifier.testTag("admob_adview")
                )
            }
        }
    }
}
