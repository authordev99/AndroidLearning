package com.teddybrothers.androidlearning.view

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import com.teddybrothers.androidlearning.R
import com.teddybrothers.androidlearning.utils.BaseActivity
import com.teddybrothers.androidlearning.utils.NetworkUtils
import kotlinx.android.synthetic.main.activity_book_now.*
import kotlinx.android.synthetic.main.toolbar.*

class BookNowActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_now)

        init()
        setupWebView()
    }

    private fun init() {
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            title = getString(R.string.app_book_now)
        }
    }

    private fun setupWebView() {
        webView.webViewClient = WebViewClient()
        webView.apply {
            settings.domStorageEnabled = true
            loadUrl(NetworkUtils.BOOK_NOW_URL)
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    view.loadUrl(url)
                    return true
                }


                override fun onPageFinished(view: WebView, url: String) {
                    if (loading != null && loading.isVisible) {
                        loading.makeGone()
                    }
                }

                override fun onReceivedError(
                    view: WebView,
                    errorCode: Int,
                    description: String,
                    failingUrl: String
                ) {
                    if (loading != null && loading.isVisible) {
                        loading.makeGone()
                    }
                }

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    if (loading != null && !loading.isVisible) {
                        loading.makeVisible()
                    }
                }
            }
        }
    }

    fun View.makeGone() {
        isVisible = false
    }

    fun View.makeVisible() {
        isVisible = true
    }


}
