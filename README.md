# WinKoKo Tunnel

![Android](https://img.shields.io/badge/Platform-Android-3DDC84?style=flat-square&logo=android)
![Kotlin](https://img.shields.io/badge/Language-Kotlin-7F52FF?style=flat-square&logo=kotlin)
![WireGuard](https://img.shields.io/badge/Protocol-WireGuard-88171A?style=flat-square&logo=wireguard)
![Gradle](https://img.shields.io/badge/Gradle-8.5-02569B?style=flat-square&logo=gradle)
![License](https://img.shields.io/badge/License-MIT-green?style=flat-square)

**WinKoKo Tunnel** သည် Cloudflare WARP Network နှင့် WireGuard Native Engine ကို အခြေခံထားသော Android VPN Application တစ်ခု ဖြစ်ပါသည်။ Network အခြေအနေအလိုက် Cloudflare Direct API နှင့် Custom Backup API တို့မှ WireGuard Config ရယူနိုင်ရန် ပြင်ဆင်ထားပြီး ချိတ်ဆက်အသုံးပြုရန် License Key သို့မဟုတ် Activation Key မလိုအပ်ပါ။

WinKoKoOo Development Team မှ တည်ဆောက်ထိန်းသိမ်းထားသော ဤ Application သည် မြန်ဆန်သော Native WireGuard backend၊ mobile network အတွက် keepalive support၊ DNS ရွေးချယ်စရာများနှင့် connection diagnostics များကို တစ်နေရာတည်းတွင် ပေးစွမ်းပါသည်။

---

## Features (ပါဝင်သော လုပ်ဆောင်ချက်များ)

- **Dual Engine Support**
  - **Cloudflare Direct API:** Cloudflare WARP service မှ တိုက်ရိုက် Config ရယူခြင်း။
  - **Custom Backup API:** Direct API အလုပ်မလုပ်သည့်အချိန်တွင် Backup Server မှ Config ရယူခြင်း။
- **WireGuard Native Integration:** WireGuard GoBackend SDK ကို တိုက်ရိုက်အသုံးပြုထားသဖြင့် မြန်ဆန်ပြီး Battery သုံးစွဲမှု သက်သာစေခြင်း။
- **Mobile Network Keepalive:** NAT နှင့် Mobile ISP network များအတွက် Persistent Keepalive ကို အသုံးပြုထားခြင်း။
- **IPv4/IPv6 Compatibility:** IPv4 နှင့် IPv6 Address များကို WireGuard Interface ထဲတွင် အသုံးပြုနိုင်ခြင်း။
- **WireGuard URI Support:** `wireguard://` URI Link များကို Copy/Paste ပြုလုပ်၍ Config Import သွင်းနိုင်ခြင်း။
- **Custom DNS Switcher:** Cloudflare DNS (`1.1.1.1`) နှင့် Google DNS (`8.8.8.8`) ကို စိတ်ကြိုက်ရွေးချယ်နိုင်ခြင်း။
- **Real-time Latency Ping:** Cloudflare နှင့် Facebook endpoint များသို့ Latency စမ်းသပ်၍ Connection အခြေအနေကို ကြည့်ရှုနိုင်ခြင်း။
- **Connection Logs:** Config ရယူမှု၊ VPN permission နှင့် tunnel state များကို အချိန်နှင့်တပြေးညီ ကြည့်ရှုနိုင်ခြင်း။
- **Modern UI & Dark Mode:** Material Design ပုံစံ၊ Dark Mode နှင့် Split Tunneling settings ပါဝင်ခြင်း။
- **Keyless Startup:** License/Activation Key မတောင်းဘဲ App ကို တိုက်ရိုက်အသုံးပြုနိုင်ခြင်း။

---

## Project Tech Stack

- **Language:** Kotlin
- **Min SDK:** 26 (Android 8.0 Oreo)
- **Target SDK:** 34 (Android 14)
- **Architecture:** Android Jetpack, Coroutines, Lifecycle Scope
- **Core Dependencies:**
  - `com.wireguard.android:tunnel` — WireGuard Android backend
  - `com.squareup.okhttp3:okhttp` — Network requests
  - `com.google.android.material:material` — User interface components

---

## Building & Installation

### Repository Clone လုပ်ရန်

```bash
git clone https://github.com/winkokooo23/warp-tunnel.git
cd warp-tunnel
```

### Debug APK Build လုပ်ရန်

```bash
./gradlew assembleDebug
```

### Release APK Build လုပ်ရန်

```bash
./gradlew assembleRelease
```

APK files များကို `app/build/outputs/apk/` အောက်တွင် ရရှိနိုင်ပါသည်။ GitHub Actions မှတစ်ဆင့်လည်း Debug နှင့် Release universal APK artifacts များကို အလိုအလျောက် build ပြုလုပ်ပေးထားပါသည်။

---

## အသုံးပြုနည်း

1. WinKoKo Tunnel ကို ဖွင့်ပြီး Android VPN permission ကို Allow ပြုလုပ်ပါ။
2. **Connect** ခလုတ်ကိုနှိပ်ပါ။ Config မရှိသေးပါက App သည် ရွေးချယ်ထားသော Engine မှ Config အသစ် ရယူပေးပါမည်။
3. Direct API အလုပ်မလုပ်ပါက **Custom Backup API** ကို Engine Settings မှ ရွေးချယ်ပြီး ပြန်လည်ချိတ်ဆက်ပါ။
4. ကိုယ်ပိုင် WireGuard Config ရှိပါက **Select Server → Add Config** မှတစ်ဆင့် Config သို့မဟုတ် `wireguard://` URI ကို Import ပြုလုပ်နိုင်ပါသည်။
5. Internet မရပါက Connection Logs၊ Active Server၊ DNS Setting နှင့် Ping Monitor တို့ကို စစ်ဆေးပါ။

> မှတ်ချက်။ VPN ချိတ်ဆက်မှုအောင်မြင်သည်ဟု ပြသခြင်းသည် tunnel interface တက်နေခြင်းကို ဆိုလိုပြီး Internet traffic ဖြတ်သန်းမှုအတွက် endpoint၊ server availability နှင့် network policy များလည်း မှန်ကန်ရပါမည်။

---

## Branding

**WinKoKoOo Development Team** မှ ပြင်ဆင်ထိန်းသိမ်းထားသော WinKoKo Tunnel ဖြစ်ပါသည်။ Cloudflare နှင့် WireGuard တို့သည် သက်ဆိုင်ရာပိုင်ရှင်များ၏ ကုန်အမှတ်တံဆိပ်များ ဖြစ်ကြပြီး ဤ Project သည် ၎င်းတို့နှင့် တရားဝင်ဆက်နွယ်မှုရှိသည်ဟု မဆိုလိုပါ။

---

## License

This project is distributed under the MIT License.

---

## Support

WinKoKoOo Tunnel ၏ နောက်ဆုံး APK နှင့် update များကို [GitHub Actions](https://github.com/winkokooo23/warp-tunnel/actions) မှ ရယူနိုင်ပါသည်။

