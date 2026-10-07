# Google Play Store Data Safety Questionnaire Guide

**Application:** Electricity Bill Calculator BD  
**Package Name:** `com.vivescriptsolutions.electricitybillcalculatorbd`  
**Publisher:** ViveScript Solutions LLC  
**Target Submission:** Google Play Console Data Safety Form

---

## Executive Summary for Google Play Reviewers

Electricity Bill Calculator BD is a standalone calculation utility. **The application itself does not collect, store, or transmit any user personal data, account information, or electricity consumption records to ViveScript Solutions LLC servers.**

The application integrates the official Google Mobile Ads SDK (AdMob) for non-intrusive advertising. All network interactions are strictly managed by Google Play Services for ad delivery.

---

## 1. Data Collection & Sharing Overview

| Question | Recommended Answer | Explanation |
| :--- | :--- | :--- |
| **Does your app collect or share any of the required user data types?** | **Yes** | Due to the inclusion of the Google Mobile Ads (AdMob) SDK. |
| **Is all of the user data collected by your app encrypted in transit?** | **Yes** | All network communication by Google Play Services uses TLS/HTTPS. |
| **Do you provide a way for users to request that their data be deleted?** | **Yes** | Users can delete local calculation history inside the app, and AdMob data follows Google's account deletion policies. |

---

## 2. Specific Data Types Declaration

### A. Location
- **Approximate location / Precise location:** **No** (The app does not declare or request location permissions).

### B. Personal Information
- **Name, Email, Address, Phone, Race/Ethnicity, Political/Religious beliefs, Sexual orientation:** **No**.

### C. Financial Information
- **User payment info, Purchase history, Credit score:** **No** (Calculations are local math; no payments processed).

### D. Health & Fitness
- **Health info, Fitness info:** **No**.

### E. Messages, Photos, Audio, Files
- **Emails, SMS, Photos, Videos, Audio recordings, Files & Docs:** **No**.

### F. App Activity
- **App interactions (Page views, taps):** **Collected by Google AdMob**
  - **Shared:** Yes (with Google AdMob / advertising partners)
  - **Purpose:** Analytics, Advertising or Marketing
  - **Optional or Required:** Required for ad delivery when online.
- **In-app search history:** **No**.

### G. App Info & Performance
- **Crash logs & Diagnostics:** **Collected by Google Play Services / AdMob**
  - **Purpose:** App functionality, Analytics
  - **Shared:** Yes (with Google)

### H. Device or Other Identifiers
- **Device or other IDs (Advertising ID / Android ID):** **Collected by Google Mobile Ads SDK**
  - **Shared:** Yes (with Google AdMob)
  - **Purpose:** Advertising or Marketing, Fraud prevention, and security
  - **Ephemerally processed:** Processed according to Google's standard advertising data policy.

---

## 3. Privacy Policy Link for Console

Enter the published URL of the Privacy Policy:  
`https://www.vivescriptsolutions.com/privacy/electricity-bill-calculator-bd`  
*(Or the direct raw GitHub repository link for `PRIVACY_POLICY.md`)*

---

## 4. Summary Table for Quick Copy-Paste

| Data Type | Collected | Shared | Ephemeral | Purpose |
| :--- | :--- | :--- | :--- | :--- |
| **Device or other IDs** | Yes | Yes | No | Advertising, Fraud Prevention |
| **App Interactions** | Yes | Yes | No | Analytics, Advertising |
| **Diagnostics / Performance** | Yes | Yes | No | Analytics, App Functionality |
| **All Other Categories** | **No** | **No** | N/A | Not collected |

---

© 2026 ViveScript Solutions LLC. All rights reserved.
