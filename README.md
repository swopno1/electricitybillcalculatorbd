# Electricity Bill Calculator BD

[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)
[![Platform](https://img.shields.io/badge/Platform-Android-3DDC84.svg)](https://developer.android.com)
[![Target SDK](https://img.shields.io/badge/Target%20SDK-36-blue.svg)](https://developer.android.com)
[![Min SDK](https://img.shields.io/badge/Min%20SDK-24-orange.svg)](https://developer.android.com)

A fast, lightweight, offline-first Android application designed to estimate monthly electricity bills in Bangladesh Taka (৳) based on consumption in kilowatt-hours (kWh). Built with **Kotlin**, **Jetpack Compose**, and **Material 3**.

Developed and published by **ViveScript Solutions LLC** ([https://www.vivescriptsolutions.com/](https://www.vivescriptsolutions.com/)).

---

## Overview

- **App Name:** Electricity Bill Calculator BD (বিদ্যুৎ বিল ক্যালকুলেটর BD)
- **Package Name:** `com.vivescriptsolutions.electricitybillcalculatorbd`
- **Primary Languages:** English, Bangla (বাংলা)
- **Target Market:** Global, with initial focus on Bangladesh, India, Pakistan, and the South Asian diaspora.
- **Core Purpose:** Open app → Enter units (kWh) → Instantly see estimated electricity bill with full slab breakdown.

---

## Key Features

1. **Instant Offline Calculations:** Enter electricity consumption in units (kWh) or tap quick presets (50, 100, 200, 300, 500, 1000) for instant results.
2. **Official BERC 2024 Slabs:**
   - **Lifeline (0–50 units):** ৳ 4.63 / unit
   - **Slab 1 (0–75 units):** ৳ 5.26 / unit
   - **Slab 2 (76–200 units):** ৳ 7.20 / unit
   - **Slab 3 (201–300 units):** ৳ 7.59 / unit
   - **Slab 4 (301–400 units):** ৳ 8.02 / unit
   - **Slab 5 (401–600 units):** ৳ 12.67 / unit
   - **Slab 6 (> 600 units):** ৳ 14.61 / unit
3. **Small Commercial (LT-E):** Flat-rate commercial estimation of ৳ 13.01 / unit.
4. **Transparent Breakdown:** Separate itemized line items for Energy Charge, Monthly Demand Charge (1 kW standard), and 5% Government VAT.
5. **Dynamic Bilingual Toggle:** One-tap switch between English and Bangla directly from the main screen, with full support for Bengali numerals (`০-৯`) and currency formatting (`৳`).
6. **Copy & Share:** Android native share sheet and clipboard copy formatted in English or Bangla.
7. **Recent History:** Locally stores the last 5 calculations with quick recall.
8. **Material Design 3:** Modern, accessible interface with Light and Dark theme support.
9. **Zero Accounts & Zero Tracking:** No login, no password, no email, and no meter number required.

---

## Technology Stack

- **Language:** Kotlin 2.2+
- **UI Framework:** Jetpack Compose (Compose BOM 2024.09.00)
- **Design System:** Material Design 3 (M3)
- **Architecture:** Clean MVVM (Model-View-ViewModel) with StateFlow
- **Precision:** `BigDecimal` with `HALF_UP` rounding for financial accuracy
- **Monetization:** Google Mobile Ads (AdMob) SDK configured with official Google test ad units
- **Build System:** Gradle Kotlin DSL (`build.gradle.kts`) with Version Catalog (`libs.versions.toml`)

---

## Requirements

- **Minimum Android Version:** Android 7.0 (Nougat, API Level 24)
- **Target Android Version:** Android 16 (API Level 36)
- **Java Compatibility:** Java 11
- **Gradle Version:** Gradle 8.11+ / AGP 9.1+

---

## Project Structure

```text
app/src/
├── main/
│   ├── java/com/example/
│   │   ├── MainActivity.kt                      # Main Activity entry point
│   │   ├── data/
│   │   │   ├── local/PreferencesRepository.kt   # Local preferences & history storage
│   │   │   └── tariff/                          # Official BERC tariff plans & slabs
│   │   │       ├── BangladeshResidentialTariff.kt
│   │   │       ├── TariffPlan.kt
│   │   │       ├── TariffSlab.kt
│   │   │       └── TariffRepository.kt
│   │   ├── domain/
│   │   │   ├── calculator/ElectricityBillCalculator.kt # Progressive calculation engine
│   │   │   └── model/                           # Data models (Breakdown, History, etc.)
│   │   ├── ui/
│   │   │   ├── calculator/                      # Calculator UI screen & ViewModel
│   │   │   ├── sheets/                          # Tariff Info and Settings bottom sheets
│   │   │   ├── theme/                           # Color, Theme, Typography (M3)
│   │   │   └── util/                            # Localized strings (AppStrings)
│   │   └── util/
│   │       └── AdConfig.kt                      # Centralized AdMob test configuration
│   ├── res/                                     # Drawables, mipmaps, values (en & bn)
│   └── AndroidManifest.xml
└── test/
    └── java/com/example/
        ├── ElectricityBillCalculatorTest.kt     # Comprehensive tariff unit tests
        └── ExampleRobolectricTest.kt            # Context & persistence unit tests
```

---

## Building and Running

### Clone Repository
```bash
git clone https://github.com/vivescriptsolutions/electricity-bill-calculator-bd.git
cd electricity-bill-calculator-bd
```

### Build Debug APK
```bash
gradle assembleDebug
```

### Run Unit Tests
```bash
gradle :app:testDebugUnitTest
```

### Build Release Android App Bundle (AAB)
```bash
gradle :app:bundleRelease
```

---

## AdMob Advertising Configuration

The application is configured with ViveScript Solutions LLC's Google AdMob credentials:
- **AdMob App ID:** `ca-app-pub-5222053984568989~6515064111`
- **Banner Ad Unit ID:** `ca-app-pub-5222053984568989/6167579759`
- **Interstitial Ad Unit ID:** `ca-app-pub-5222053984568989/9212634784`

Centralized ad configuration is managed in `app/src/main/java/com/example/util/AdConfig.kt`.
Interstitial frequency is managed by `InterstitialAdManager.kt` following strict AdMob policy guidelines (cooldown intervals, action capping, and non-intrusive presentation).

---

## Privacy Policy & Legal

- [Privacy Policy](PRIVACY_POLICY.md)
- [Terms of Service](TERMS_OF_SERVICE.md)
- [Play Store Metadata & ASO](PLAY_STORE_METADATA.md)
- [Google Play Data Safety Guide](PLAY_STORE_DATA_SAFETY.md)
- [Design & Graphic Assets](DESIGN_ASSETS.md)
- [Changelog](CHANGELOG.md)

---

## Licensing

- **Source Code:** Licensed under the [MIT License](LICENSE).
- **Proprietary Branding:** The application name ("Electricity Bill Calculator BD", "বিদ্যুৎ বিল ক্যালকুলেটর BD"), logos, icons, screenshots, and visual branding are proprietary property of **ViveScript Solutions LLC** and are not licensed under the MIT License.

---

## Copyright

**© 2026 ViveScript Solutions LLC.** All rights reserved.  
Website: [https://www.vivescriptsolutions.com/](https://www.vivescriptsolutions.com/)
