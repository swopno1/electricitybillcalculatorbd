package com.example.ui.util

import com.example.domain.model.AppLanguage

object AppStrings {

    fun appTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "বিদ্যুৎ বিল ক্যালকুলেটর BD"
        AppLanguage.ENGLISH -> "Electricity Bill Calculator BD"
    }

    fun appSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "বাংলাদেশে আপনার মাসিক বিদ্যুৎ বিল হিসাব করুন"
        AppLanguage.ENGLISH -> "Estimate your monthly electricity bill in Bangladesh"
    }

    fun residential(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "আবাসিক"
        AppLanguage.ENGLISH -> "Residential"
    }

    fun smallCommercial(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "ক্ষুদ্র বাণিজ্যিক"
        AppLanguage.ENGLISH -> "Small Commercial"
    }

    fun tariffTypeLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "সংযোগের ধরন"
        AppLanguage.ENGLISH -> "Tariff Category"
    }

    fun monthlyUsageLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "ব্যবহৃত ইউনিট (kWh)"
        AppLanguage.ENGLISH -> "Units consumed (kWh)"
    }

    fun unitHint(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "১ ইউনিট = ১ kWh"
        AppLanguage.ENGLISH -> "1 unit = 1 kWh"
    }

    fun calculateButton(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "হিসাব করুন"
        AppLanguage.ENGLISH -> "Calculate"
    }

    fun resetButton(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "রিসেট"
        AppLanguage.ENGLISH -> "Reset"
    }

    fun estimatedBillTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "আনুমানিক বিল"
        AppLanguage.ENGLISH -> "Estimated Bill"
    }

    fun energyChargeLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "বিদ্যুৎ মূল্য (Energy Charge)"
        AppLanguage.ENGLISH -> "Energy charge"
    }

    fun demandChargeLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "ডিমান্ড চার্জ (Demand Charge)"
        AppLanguage.ENGLISH -> "Demand charge"
    }

    fun vatLabel(lang: AppLanguage, percentage: Double): String = when (lang) {
        AppLanguage.BANGLA -> "ভ্যাট (${percentage.toInt()}%)"
        AppLanguage.ENGLISH -> "VAT (${percentage.toInt()}%)"
    }

    fun totalLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "সর্বমোট আনুমানিক বিল"
        AppLanguage.ENGLISH -> "Estimated total"
    }

    fun billBreakdownTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "ধাপভিত্তিক বিস্তারিত হিসাব"
        AppLanguage.ENGLISH -> "Bill Breakdown"
    }

    fun hideBreakdown(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "সংক্ষেপ করুন"
        AppLanguage.ENGLISH -> "Hide Breakdown"
    }

    fun viewBreakdown(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "বিস্তারিত দেখুন"
        AppLanguage.ENGLISH -> "View Breakdown"
    }

    fun slabHeader(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "ধাপ"
        AppLanguage.ENGLISH -> "Slab"
    }

    fun unitsHeader(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "ইউনিট"
        AppLanguage.ENGLISH -> "Units"
    }

    fun rateHeader(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "দর (টাকা)"
        AppLanguage.ENGLISH -> "Rate (৳)"
    }

    fun amountHeader(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "টাকা"
        AppLanguage.ENGLISH -> "Amount"
    }

    fun copyResult(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "কপি করুন"
        AppLanguage.ENGLISH -> "Copy Result"
    }

    fun shareResult(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "শেয়ার করুন"
        AppLanguage.ENGLISH -> "Share"
    }

    fun copiedToast(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "হিসাব ক্লিপবোর্ডে কপি করা হয়েছে"
        AppLanguage.ENGLISH -> "Calculation copied to clipboard"
    }

    fun disclaimer(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "এটি একটি আনুমানিক হিসাব। ট্যারিফ, মিটার, নির্দিষ্ট চার্জ, ভ্যাট, সার্ভিস চার্জ ও অন্যান্য সমন্বয়ের কারণে আপনার প্রকৃত বিল কিছুটা ভিন্ন হতে পারে।"
        AppLanguage.ENGLISH -> "Estimated amount only. Your actual bill may differ due to tariff category, meter type, demand/fixed charges, VAT, service charges, adjustments, and government tariff changes."
    }

    fun tariffNotice(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "এই হিসাব নির্বাচিত বাংলাদেশি ট্যারিফের (বিইআরসি ২০২৪) ভিত্তিতে করা হয়। আপনার প্রকৃত বিল কিছুটা ভিন্ন হতে পারে।"
        AppLanguage.ENGLISH -> "Tariff rates are based on the selected Bangladesh tariff schedule (BERC 2024) and may differ from your actual electricity bill."
    }

    fun quickPresets(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "দ্রুত নির্বাচন"
        AppLanguage.ENGLISH -> "Quick Presets"
    }

    fun demandChargeToggle(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "ডিমান্ড চার্জ অন্তর্ভুক্ত (১ kW)"
        AppLanguage.ENGLISH -> "Include demand charge (1 kW)"
    }

    fun recentCalculations(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "সাম্প্রতিক হিসাব"
        AppLanguage.ENGLISH -> "Recent Calculations"
    }

    fun clearHistory(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "মুছে ফেলুন"
        AppLanguage.ENGLISH -> "Clear"
    }

    fun tariffInfoTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "ট্যারিফ রেট ও নিয়মাবলী"
        AppLanguage.ENGLISH -> "Tariff Information"
    }

    fun settingsTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "সেটিংস"
        AppLanguage.ENGLISH -> "Settings"
    }

    fun languageLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "ভাষা (Language)"
        AppLanguage.ENGLISH -> "Language"
    }

    fun themeLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "থিম (Theme)"
        AppLanguage.ENGLISH -> "Theme"
    }

    fun themeSystem(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "সিস্টেম অনুযায়ী"
        AppLanguage.ENGLISH -> "System Default"
    }

    fun themeLight(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "লাইট"
        AppLanguage.ENGLISH -> "Light"
    }

    fun themeDark(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "ডার্ক"
        AppLanguage.ENGLISH -> "Dark"
    }

    fun aboutTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "অ্যাপ পরিচিতি ও কোম্পানি"
        AppLanguage.ENGLISH -> "About & Company"
    }

    fun developedBy(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "ডেভেলপার: ViveScript Solutions LLC"
        AppLanguage.ENGLISH -> "Developed by: ViveScript Solutions LLC"
    }

    fun websiteUrl(): String = "https://www.vivescriptsolutions.com/"

    fun copyrightNotice(): String = "© 2026 ViveScript Solutions LLC."

    fun sourceCodeLicense(): String = "Source code: MIT License"

    fun brandingNotice(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "অ্যাপের নাম, লোগো, আইকন, স্ক্রিনশট এবং ব্র্যান্ডিং ViveScript Solutions LLC-এর নিজস্ব সম্পত্তি এবং এমআইটি লাইসেন্সের অন্তর্ভুক্ত নয়।"
        AppLanguage.ENGLISH -> "The app name, logo, icons, screenshots, trademarks, and associated branding are proprietary property of ViveScript Solutions LLC and are not licensed under the MIT License."
    }

    fun aboutDescription(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "বিদ্যুৎ বিল ক্যালকুলেটর BD একটি নির্ভরযোগ্য ও স্বাধীন ইউটিলিটি অ্যাপ্লিকেশন। এটি বাংলাদেশ এনার্জি রেগুলেটরি কমিশন (BERC) এর সর্বশেষ সরকারি গেজেট (মার্চ ২০২৪) অনুসারে ধাপভিত্তিক আনুমানিক বিল হিসাব করে। আপনার কোনো ব্যক্তিগত তথ্য সংগ্রহ করা হয় না এবং গণনা সম্পূর্ণ অফলাইনে সম্পাদিত হয়।"
        AppLanguage.ENGLISH -> "Electricity Bill Calculator BD is a reliable, privacy-first utility application that calculates progressive monthly electricity estimates based on official BERC 2024 retail schedules. No personal data is collected, and calculations work completely offline."
    }

    fun emptyInputError(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "অনুগ্রহ করে আপনার ব্যবহৃত ইউনিট লিখুন।"
        AppLanguage.ENGLISH -> "Enter your monthly electricity usage."
    }

    fun negativeInputError(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "ইউনিট ঋণাত্মক হতে পারে না।"
        AppLanguage.ENGLISH -> "Usage cannot be negative."
    }

    fun largeInputError(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "অনুগ্রহ করে সংখ্যাটি যাচাই করুন (সর্বোচ্চ ৫০,০০০)।"
        AppLanguage.ENGLISH -> "Please check the entered value (max 50,000)."
    }

    fun invalidNumberError(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "সঠিক সংখ্যা লিখুন।"
        AppLanguage.ENGLISH -> "Please enter a valid number."
    }

    fun close(lang: AppLanguage): String = when (lang) {
        AppLanguage.BANGLA -> "বন্ধ করুন"
        AppLanguage.ENGLISH -> "Close"
    }
}
