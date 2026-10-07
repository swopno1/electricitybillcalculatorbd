package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.local.PreferencesRepository
import com.example.domain.model.AppLanguage
import com.example.domain.model.CalculationHistoryItem
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

    @Test
    fun `read app name from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Electricity Bill Calculator BD", appName)
    }

    @Test
    fun `preferences repository language and history persistence`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val repo = PreferencesRepository(context)

        // Test language persistence
        repo.setLanguage(AppLanguage.ENGLISH)
        assertEquals(AppLanguage.ENGLISH, repo.getLanguage())

        repo.setLanguage(AppLanguage.BANGLA)
        assertEquals(AppLanguage.BANGLA, repo.getLanguage())

        // Test history persistence
        repo.clearHistory()
        assertEquals(0, repo.getHistory().size)

        repo.addHistoryItem(
            CalculationHistoryItem(
                id = "1",
                timestamp = System.currentTimeMillis(),
                units = 150.0,
                totalAmount = 1050.0,
                planNameEn = "Residential",
                planNameBn = "আবাসিক"
            )
        )
        val history = repo.getHistory()
        assertEquals(1, history.size)
        assertEquals(150.0, history[0].units, 0.001)
    }
}
