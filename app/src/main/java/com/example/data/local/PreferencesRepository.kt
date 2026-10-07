package com.example.data.local

import android.content.Context
import android.content.SharedPreferences
import com.example.domain.model.AppLanguage
import com.example.domain.model.AppThemeMode
import com.example.domain.model.CalculationHistoryItem
import org.json.JSONArray
import org.json.JSONObject

class PreferencesRepository(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "elec_bill_bd_prefs"
        private const val KEY_LANGUAGE = "key_language"
        private const val KEY_THEME = "key_theme"
        private const val KEY_SANCTIONED_LOAD = "key_sanctioned_load"
        private const val KEY_INCLUDE_DEMAND = "key_include_demand"
        private const val KEY_HISTORY = "key_history"
        private const val MAX_HISTORY_ITEMS = 5
    }

    fun getLanguage(): AppLanguage {
        val code = prefs.getString(KEY_LANGUAGE, AppLanguage.BANGLA.code)
        return if (code == AppLanguage.ENGLISH.code) AppLanguage.ENGLISH else AppLanguage.BANGLA
    }

    fun setLanguage(language: AppLanguage) {
        prefs.edit().putString(KEY_LANGUAGE, language.code).apply()
    }

    fun getThemeMode(): AppThemeMode {
        val name = prefs.getString(KEY_THEME, AppThemeMode.SYSTEM.name)
        return try {
            AppThemeMode.valueOf(name ?: AppThemeMode.SYSTEM.name)
        } catch (_: Exception) {
            AppThemeMode.SYSTEM
        }
    }

    fun setThemeMode(mode: AppThemeMode) {
        prefs.edit().putString(KEY_THEME, mode.name).apply()
    }

    fun getSanctionedLoad(): Double {
        return prefs.getFloat(KEY_SANCTIONED_LOAD, 1.0f).toDouble()
    }

    fun setSanctionedLoad(loadKw: Double) {
        prefs.edit().putFloat(KEY_SANCTIONED_LOAD, loadKw.toFloat()).apply()
    }

    fun getIncludeDemandCharge(): Boolean {
        return prefs.getBoolean(KEY_INCLUDE_DEMAND, true)
    }

    fun setIncludeDemandCharge(include: Boolean) {
        prefs.edit().putBoolean(KEY_INCLUDE_DEMAND, include).apply()
    }

    fun getHistory(): List<CalculationHistoryItem> {
        val jsonString = prefs.getString(KEY_HISTORY, null) ?: return emptyList()
        return try {
            val jsonArray = JSONArray(jsonString)
            val list = mutableListOf<CalculationHistoryItem>()
            for (i in 0 until jsonArray.length()) {
                val obj = jsonArray.getJSONObject(i)
                list.add(
                    CalculationHistoryItem(
                        id = obj.getString("id"),
                        timestamp = obj.getLong("timestamp"),
                        units = obj.getDouble("units"),
                        totalAmount = obj.getDouble("totalAmount"),
                        planNameEn = obj.getString("planNameEn"),
                        planNameBn = obj.getString("planNameBn")
                    )
                )
            }
            list
        } catch (_: Exception) {
            emptyList()
        }
    }

    fun addHistoryItem(item: CalculationHistoryItem) {
        val current = getHistory().toMutableList()
        // Remove duplicate if same units calculated consecutively
        current.removeAll { it.units == item.units && it.planNameEn == item.planNameEn }
        current.add(0, item)
        val trimmed = current.take(MAX_HISTORY_ITEMS)

        val jsonArray = JSONArray()
        for (hist in trimmed) {
            val obj = JSONObject()
            obj.put("id", hist.id)
            obj.put("timestamp", hist.timestamp)
            obj.put("units", hist.units)
            obj.put("totalAmount", hist.totalAmount)
            obj.put("planNameEn", hist.planNameEn)
            obj.put("planNameBn", hist.planNameBn)
            jsonArray.put(obj)
        }
        prefs.edit().putString(KEY_HISTORY, jsonArray.toString()).apply()
    }

    fun clearHistory() {
        prefs.edit().remove(KEY_HISTORY).apply()
    }
}
