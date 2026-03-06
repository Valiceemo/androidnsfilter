# personalDNSfilter (Tasker Automation Fork)

> **⚠️ ATTENTION:** This is a custom fork of the fantastic [personalDNSfilter](https://github.com/IngoZenz/personaldnsfilter) created by **IngoZenz**. All credit for the core DNS engine, VPN filtering architecture, and main application belongs entirely to the original developer. Please visit the official repository to support the original project!

## 🤖 What's different in this fork?
This specific fork introduces a native Intent API, allowing power users to fully automate the VPN using apps like **Tasker, MacroDroid, or Automate**. 

Due to strict background execution limits introduced in Android 14+ (and heavily enforced in Android 16), background apps and `BroadcastReceivers` are generally blocked from starting foreground VPN services. This fork bypasses that limitation by utilizing a custom, completely invisible floating "Trampoline Activity" (`TaskerActivity`). It briefly wakes up to legally lock the `VpnService` interface and start/stop the filter, then instantly vanishes without triggering any system window animations, black flashes, or screen dimming.

## ⚙️ How to Automate (Tasker Instructions)

To control the filter, use the **System -> Send Intent** action in Tasker. Ensure Tasker has the "Display over other apps" permission granted in your Android settings.

*(Note: You must open the app and start the filter manually at least once after installing so Android can prompt you for the standard VPN connection permission).*

### ▶️ To START the Filter:
* **Action:** `dnsfilter.android.intent.START`
* **Cat:** `Default`
* **Package:** `dnsfilter.android`
* **Class:** *(leave completely blank)*
* **Target:** `Activity`

### 🛑 To STOP the Filter:
* **Action:** `dnsfilter.android.intent.STOP`
* **Cat:** `Default`
* **Package:** `dnsfilter.android`
* **Class:** *(leave completely blank)*
* **Target:** `Activity`

## 🛠️ Installation
Because this is an unofficial fork, you cannot install it over the top of the official Google Play Store or F-Droid release due to differing cryptographic signatures.
1. Uninstall the official personalDNSfilter app.
2. Download the latest custom APK from the **Actions** or **Releases** tab of this repository.
3. Install the APK and grant standard VPN permissions.

---
*For all documentation regarding the core DNS filtering engine, blocklists, and advanced configuration, please see the [official personalDNSfilter documentation](https://www.zenz-solutions.de/personaldnsfilter/).*
