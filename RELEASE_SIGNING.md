# Release Signing Configuration

**Application:** Electricity Bill Calculator BD  
**Package Name:** `com.vivescriptsolutions.electricitybillcalculatorbd`  
**Publisher:** ViveScript Solutions LLC  
**Copyright:** © 2026 ViveScript Solutions LLC  

---

## Overview

Google Play Console requires all production APKs and Android App Bundles (.aab) to be signed in **release mode** with a valid release certificate. Google Play explicitly rejects binaries signed with the Android debug certificate (`CN=Android Debug`, alias `androiddebugkey`).

This project includes a dedicated production-grade release keystore configured to sign the App Bundle and APK automatically during release builds.

---

## Release Keystore Specifications

| Attribute | Value |
| :--- | :--- |
| **Keystore File** | `release.keystore` (in project root) |
| **Backup File** | `release.keystore.base64` |
| **Key Alias** | `vivescript_release` |
| **Store Password** | `vivescript2026` |
| **Key Password** | `vivescript2026` |
| **Key Algorithm** | RSA 2048-bit |
| **Signature Algorithm** | SHA384withRSA |
| **Certificate Validity** | 10,000 days (Valid until Feb 23, 2054) |
| **Certificate Subject / Owner** | `CN=ViveScript Solutions LLC, OU=Mobile Engineering, O=ViveScript Solutions LLC, L=Wilmington, ST=Delaware, C=US` |
| **SHA-1 Fingerprint** | `F9:E3:97:B3:FB:92:B0:99:68:E1:00:1C:5B:DE:DB:3A:E1:B9:DC:90` |
| **SHA-256 Fingerprint** | `6E:EC:25:A2:DF:16:92:FB:AF:57:A6:4E:C9:07:73:43:3D:DB:64:24:9F:09:A1:06:2B:B3:9A:35:B6:13:AB:D7` |

---

## How to Build the Release Bundle (.aab)

To generate the signed release App Bundle for Google Play Console:

```bash
gradle :app:bundleRelease
```

The output file is located at:
```text
app/build/outputs/bundle/release/app-release.aab
```

And staged ready for download at:
```text
release_bundle/electricity-bill-calculator-bd.aab
```

---

## How to Build the Release APK (.apk)

To generate a standalone signed release APK:

```bash
gradle :app:assembleRelease
```

The output APK is located at:
```text
app/build/outputs/apk/release/app-release.apk
```

---

## Verifying the Signature

You can verify that the generated binary is signed in release mode using `apksigner` or `jarsigner`:

```bash
# Verify AAB or APK with jarsigner
jarsigner -verify -verbose -certs app/build/outputs/bundle/release/app-release.aab

# Inspect keystore entry
keytool -list -v -keystore release.keystore -alias vivescript_release -storepass vivescript2026
```

---

## Using Custom CI/CD Environment Variables

If you maintain a separate proprietary key in your CI/CD pipeline (e.g., GitHub Actions, Bitrise, Fastlane), `app/build.gradle.kts` supports custom overrides via environment variables:

- `KEYSTORE_PATH`: Absolute or relative path to your custom `.jks` or `.keystore` file.
- `STORE_PASSWORD`: Password for the keystore.
- `KEY_ALIAS`: Alias of the key entry (default: `vivescript_release`).
- `KEY_PASSWORD`: Password for the key (defaults to `STORE_PASSWORD`).

When these environment variables are absent, Gradle automatically uses `release.keystore` from the root directory with the built-in release configuration.
