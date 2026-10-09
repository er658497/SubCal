package com.example.crewschedule

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.util.Base64
import android.webkit.WebChromeClient
import android.webkit.JsResult
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val web = WebView(this).apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.allowFileAccess = true
            settings.allowContentAccess = true
            webViewClient = WebViewClient()
            webChromeClient = object : WebChromeClient() {
                override fun onJsAlert(
                    view: WebView?,
                    url: String?,
                    message: String?,
                    result: JsResult
                ): Boolean {
                    AlertDialog.Builder(this@MainActivity)
                        .setTitle("SubCal")
                        .setMessage(message.orEmpty())
                        .setPositiveButton(android.R.string.ok) { _, _ -> result.confirm() }
                        .setOnCancelListener { result.cancel() }
                        .show()
                    return true
                }

                override fun onJsConfirm(
                    view: WebView?,
                    url: String?,
                    message: String?,
                    result: JsResult
                ): Boolean {
                    AlertDialog.Builder(this@MainActivity)
                        .setTitle("SubCal")
                        .setMessage(message.orEmpty())
                        .setPositiveButton("Yes") { _, _ -> result.confirm() }
                        .setNegativeButton("Cancel") { _, _ -> result.cancel() }
                        .setOnCancelListener { result.cancel() }
                        .show()
                    return true
                }
            }
        }
        fun b64(value: String) =
            Base64.encodeToString(value.toByteArray(Charsets.UTF_8), Base64.NO_WRAP)
        val html = assets.open("index.html").bufferedReader().use { it.readText() }
            .replace("__SUPABASE_URL_B64__", b64(BuildConfig.SUPABASE_URL))
            .replace("__SUPABASE_KEY_B64__", b64(BuildConfig.SUPABASE_ANON_KEY))
            .replace("__SCHEDULE_ID_B64__", b64(BuildConfig.SCHEDULE_ID))
        web.loadDataWithBaseURL("file:///android_asset/", html, "text/html", "UTF-8", null)
        setContentView(FrameLayout(this).apply {
            addView(web, FrameLayout.LayoutParams(-1, -1))
        })
    }
}