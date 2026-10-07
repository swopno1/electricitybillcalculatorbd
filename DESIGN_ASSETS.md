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

- **Dimensions:** 1024 x 500 px (Landscape)
- **Format:** 24-bit PNG or JPEG (no alpha)
- **Maximum File Size:** 15 MB
- **Composition:**
  - **Background:** Rich gradient shifting from deep forest green (`#063825`) to dark emerald (`#0B6645`).
  - **Left / Center Side:** Bold headline in clean typography:
    - *"Electricity Bill Calculator BD"*
    - Subtitle: *"Fast, Transparent & Offline • Official BERC Slabs"*
  - **Right Side:** High-fidelity tilted mockup of the main bill estimation card highlighting `৳ 2,852` with the golden lightning symbol.
  - **Bottom Corner:** Subtle brand mark: *"ViveScript Solutions LLC"*.

---

## 3. Google Play Screenshots Sequence

Recommended 6-screenshot set for Google Play Store listing (1080 x 2400 px, 20:9 ratio):

### Screenshot 1: Instant Bill Calculation
- **Headline:** Instant Electricity Bill Estimation
- **Subtitle:** Enter your monthly units (kWh) and see your bill in seconds.
- **Visual:** Main screen with `100 kWh` entered and prominent `৳ 647.33` result card.

### Screenshot 2: Progressive Slab Breakdown
- **Headline:** Transparent Slab-by-Slab Breakdown
- **Subtitle:** Understand every step of your bill under official BERC tariffs.
- **Visual:** Expanded bill breakdown table showing Lifeline, Slab 1, Slab 2, Demand charge, and 5% VAT.

### Screenshot 3: One-Tap Quick Presets
- **Headline:** Quick Presets for Fast Calculations
- **Subtitle:** One tap for 50, 100, 200, 300, 500, or 1000 units.
- **Visual:** Focus on the horizontal quick-preset chips row.

### Screenshot 4: Full Bilingual Support (বাংলা ও English)
- **Headline:** সম্পূর্ণ বাংলা এবং ইংরেজি ইন্টারফেস
- **Subtitle:** বাংলা সংখ্যা (০-৯) ও টাকা (৳) প্রতীকের পূর্ণাঙ্গ সাপোর্ট।
- **Visual:** Complete screen in Bangla mode displaying `৳ ২,৮৫২`.

### Screenshot 5: 100% Offline & Private
- **Headline:** Works Completely Offline
- **Subtitle:** No login, no accounts, and no data tracking.
- **Visual:** Highlight showing offline readiness badge and zero registration requirement.

### Screenshot 6: Easy Share & History
- **Headline:** One-Tap Share & Recent History
- **Subtitle:** Share itemized bills with roommates and recall previous calculations.
- **Visual:** Native Android share sheet and the 5-item recent calculations card.

---

## 4. Typography Guidelines
- **English Display & Body:** Roboto / System Material 3 typography with clean weights (`Bold`, `SemiBold`, `Medium`, `Regular`).
- **Bangla Typography:** Noto Sans Bengali / Kohinoor Bangla fallback with accurate rendering of compound conjuncts and Bengali numerals.

---

© 2026 ViveScript Solutions LLC. All rights reserved.
