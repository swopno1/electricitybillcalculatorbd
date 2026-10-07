# Changelog

All notable changes to **Electricity Bill Calculator BD** are documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

---

## [1.0.0] - 2026-10-07

### Added
- **Core Calculation Engine**: Progressive slab-based electricity bill estimation using official Bangladesh Energy Regulatory Commission (BERC) retail tariff schedules (March 2024 Gazette).
- **Tariff Categories**:
  - Residential (LT-A) with Lifeline support (0–50 units) and 6 progressive consumption steps.
  - Small Commercial (LT-E) flat-rate calculation for small businesses and shops.
- **Detailed Bill Breakdown**: Itemized calculation of Energy Charge, Demand Charge (1 kW standard), 5% Government VAT, and total estimated amount.
- **Bilingual Interface**: In-app toggle between English and Bangla (বাংলা), with full support for Bengali numerals (`০-৯`) and Bangladesh Taka currency formatting (`৳`).
- **Quick Preset Chips**: Fast one-tap inputs for 50, 100, 200, 300, 500, and 1000 kWh units.
- **Copy & Share**: Native Android share sheet and clipboard copy formatted cleanly in both English and Bangla.
- **Recent Calculations**: On-device persistence of the last 5 calculations with instant recall and clear option.
- **Settings & Tariff Sheet**: In-app tariff rates reference sheet and settings modal (language, theme, demand charge toggle, and company information).
- **AdMob Integration**: Non-intrusive banner ad container utilizing Google's official test ad configuration.
- **Theme Support**: Material Design 3 dynamic styling with Light and Dark mode support.
- **Offline First**: Complete offline mathematical functionality with zero required network calls and zero authentication.

---

© 2026 ViveScript Solutions LLC. All rights reserved.
