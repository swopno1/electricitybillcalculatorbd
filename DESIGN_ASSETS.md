# Design & Graphic Assets Specification

**Application:** Electricity Bill Calculator BD  
**Package:** `com.vivescriptsolutions.electricitybillcalculatorbd`  
**Publisher:** ViveScript Solutions LLC (`https://www.vivescriptsolutions.com/`)  
**Design System:** Material Design 3 (M3)  
**Primary Brand Colors:**  
- **Forest Emerald (Primary):** `#0B6645` (Light) / `#67DCAB` (Dark)  
- **Container Mint:** `#CEEBDC` (Light) / `#005136` (Dark)  
- **Electric Amber (Accent):** `#8B5A00` (Light) / `#FFB951` (Dark)  
- **Icon Solid Background:** `#0D5C3E`

---

## 1. App Launcher Icon Specification

### Adaptive Icon (Android 8.0+)
- **Canvas Size:** 108 x 108 dp
- **Safe Zone:** Central 66 dp diameter
- **Background Layer:** Solid `#0D5C3E` (`res/drawable/ic_launcher_background.xml`)
- **Foreground Layer:** Minimalist golden lightning bolt intertwined with a clean document bill receipt silhouette centered inside 66 dp safe zone (`res/drawable/ic_launcher_foreground.xml`)
- **Adaptive Wrapper:** `res/mipmap-anydpi-v26/ic_launcher.xml` and `ic_launcher_round.xml`

### Legacy Raster & Store Icon Assets
- **Play Store Hi-Res Icon:** 512 x 512 px, 32-bit PNG with alpha, maximum file size 1024 KB.
- **Mipmap Fallback Bitmaps:**
  - `mdpi`: 48 x 48 px
  - `hdpi`: 72 x 72 px
  - `xhdpi`: 96 x 96 px
  - `xxhdpi`: 144 x 144 px
  - `xxxhdpi`: 192 x 192 px

---

## 2. Google Play Feature Graphic

- **File Location:** `play_store_assets/feature_graphic.png` (and `app/src/main/res/drawable/play_feature_graphic.png`)
- **Dimensions:** 1024 x 500 px (Landscape)
- **Format:** 24-bit PNG (no alpha, exactly matching Google Play Console specifications)
- **File Size:** ~557 KB (< 15 MB limit)
- **Visual Composition:**
  - Modern minimalist dark forest & emerald green gradient background.
  - Glowing golden electric bolt intertwined with clean white receipt bill document featuring the Bangladesh Taka (৳) symbol.
  - High-contrast, professional Android utility presentation suitable for top-of-listing Google Play merchandising.

---

## 3. Google Play Store App Icon

- **File Location:** `play_store_assets/icon_512x512.png` (and `app/src/main/res/drawable/play_store_icon.png`)
- **Dimensions:** 512 x 512 px
- **Format:** 32-bit PNG with RGBA (< 1024 KB limit)
- **Visual Identity:**
  - Solid `#0D5C3E` emerald background.
  - Centered golden lightning bolt emblem intertwined with minimalist document receipt silhouette.
  - Crisp, scalable silhouette for high recognition on mobile screens and Play Store web pages.

---

## 4. Google Play Screenshots Sequence (Live Attached Captures)

The following 4 official high-resolution screenshots are captured directly from the live application and prepared for Google Play Console submission (1080 x 2400 px, 20:9 aspect ratio):

### Screenshot 1 (`screenshot_1.png`): Main Calculation & Estimated Bill Result
- **Headline (EN):** Instant Electricity Bill Estimation
- **Headline (BN):** তাৎক্ষণিক বিদ্যুৎ বিল হিসাব
- **Key Display:**
  - Numeric usage input: `100 kWh`
  - Prominent result card: **৳ ৬৪৭.৩৩** (100 ইউনিট)
  - Itemized summary: Energy Charge `৳ ৫৭৪.৫০`, Demand Charge `৳ ৪২`, VAT `৳ ৩০.৮৩`
  - Quick-preset chips (`৫০`, `১০০`, `২০০`, `৩০০`, `৫০০`, `১০০০ kWh`)
  - One-tap Copy and Share actions

### Screenshot 2 (`screenshot_2.png`): Progressive Slab Breakdown & Ad Placement
- **Headline (EN):** Transparent Slab-by-Slab Breakdown
- **Headline (BN):** ধাপভিত্তিক স্বচ্ছ হিসাব
- **Key Display:**
  - Expanded progressive tariff breakdown table:
    - ১ম ধাপ (০ – ৭৫ ইউনিট) @ ৳ ৫.২৬ = ৳ ৩৯৪.৫০
    - ২য় ধাপ (৭৬ – ২০০ ইউনিট) @ ৳ ৭.২০ = ৳ ১৮০.০০
  - BERC 2024 official tariff schedule disclaimer
  - Non-intrusive AdMob banner placement with clear "বিজ্ঞাপন" sponsorship label
  - On-device recent calculations history

### Screenshot 3 (`screenshot_3.png`): Settings, Theming & Company Profile
- **Headline (EN):** Simple Settings & Company Profile
- **Headline (BN):** সহজ সেটিংস ও কোম্পানি পরিচিতি
- **Key Display:**
  - Instant language selector: বাংলা / English
  - Display theme selection: সিস্টেম অনুযায়ী / লাইট / ডার্ক
  - Demand charge toggle switch (১ kW)
  - Developer branding: **ViveScript Solutions LLC** (`https://www.vivescriptsolutions.com/`)
  - © 2026 ViveScript Solutions LLC copyright & MIT License notice

### Screenshot 4 (`screenshot_4.png`): Official BERC Tariff Information Sheet
- **Headline (EN):** Official Bangladesh Tariff Schedule
- **Headline (BN):** সরকারি গেজেট ও ট্যারিফ নিয়মাবলী
- **Key Display:**
  - BERC & Power Division authority attribution
  - Gazette reference: S.R.O. No. 51-Act/2024 (Effective March 1, 2024)
  - Demand charge & 5% VAT rate specifications
  - Full residential rate schedule:
    - Lifeline (1–50 units): ৳ 4.63 / kWh
    - Slab 1 (0–75 units): ৳ 5.26 / kWh
    - Slab 2 (76–200 units): ৳ 7.20 / kWh
    - Slab 3 (201–300 units): ৳ 7.59 / kWh
    - Slab 4 (301–400 units): ৳ 8.02 / kWh
    - Slab 5 (401–600 units): ৳ 12.67 / kWh
    - Slab 6 (> 600 units): ৳ 14.61 / kWh
  - Distribution company coverage (DESCO, DPDC, BPDB, BREB, NESCO, WZPDCL)

---

## 4. Typography Guidelines
- **English Display & Body:** Roboto / System Material 3 typography with clean weights (`Bold`, `SemiBold`, `Medium`, `Regular`).
- **Bangla Typography:** Noto Sans Bengali / Kohinoor Bangla fallback with accurate rendering of compound conjuncts and Bengali numerals.

---

© 2026 ViveScript Solutions LLC. All rights reserved.
