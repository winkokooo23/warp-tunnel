package com.myanmar.warpvpn

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.util.concurrent.TimeUnit

class AuthManager(private val context: Context) {

    companion object {
        init {
            try {
                System.loadLibrary("warpvpn")
            } catch (e: Throwable) {
                Log.e("AuthManager", "Failed to load native library: ${e.message}")
            }
        }
    }

    private external fun getNativeWorkerApiUrl(): String

    private val workerApiUrl: String
        get() {
            return try {
                getNativeWorkerApiUrl()
            } catch (e: Throwable) {
                Log.e("AuthManager", "Native URL Call Error: ${e.message}")
                "https://invalid-api-url.local/api/check-license"
            }
        }

    private val client = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .build()

    private val prefs = context.getSharedPreferences("WARP_VPN_PREFS", Context.MODE_PRIVATE)

    suspend fun checkLicenseServer(hwid: String, inputSerialKey: String? = null): Pair<Boolean, String> =
        Pair(true, "Keyless mode enabled")

    fun isLocalLicenseValid(): Boolean = true

    fun getSavedSerialKey(): String {
        return prefs.getString("SAVED_SERIAL_KEY", "NOT_ACTIVATED") ?: "NOT_ACTIVATED"
    }

    fun getSavedExpireDate(): Long = Long.MAX_VALUE

    fun clearLicenseData() {
        // No license state is used in keyless mode.
    }
}
