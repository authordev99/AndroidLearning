package com.teddybrothers.androidlearning.view

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.teddybrothers.androidlearning.R
import kotlinx.android.synthetic.main.activity_book_now.*

class BookNowActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_now)

        title = "Book Now"
        setupWebView()
    }

    fun setupWebView(){
        webView.webViewClient = WebViewClient()
        webView.apply {
            settings.domStorageEnabled = true
            loadUrl("https://www.cathaycineplexes.com.sg/")
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

                override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        if (itemId == android.R.id.home) // Press Back Icon
        {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    fun View.makeGone()
    {
        isVisible = false
    }

    fun View.makeVisible()
    {
        isVisible = true
    }


}
