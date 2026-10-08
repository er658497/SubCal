package com.example.crewschedule

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout

class MainActivity : Activity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val web = WebView(this).apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.allowFileAccess = true
            settings.allowContentAccess = true
            webViewClient = WebViewClient()
        }
        val html = assets.open("index.html").bufferedReader().use { it.readText() }
            .replace("__SUPABASE_URL__", BuildConfig.SUPABASE_URL.replace("\\", "\\\\").replace(""", "\\""))
            .replace("__SUPABASE_KEY__", BuildConfig.SUPABASE_ANON_KEY.replace("\\", "\\\\").replace(""", "\\""))
            .replace("crew-schedule-shared", BuildConfig.SCHEDULE_ID.replace("\\", "\\\\").replace(""", "\\""))
        web.loadDataWithBaseURL("file:///android_asset/", html, "text/html", "UTF-8", null)
        setContentView(FrameLayout(this).apply { addView(web, FrameLayout.LayoutParams(-1, -1)) })
    }
}