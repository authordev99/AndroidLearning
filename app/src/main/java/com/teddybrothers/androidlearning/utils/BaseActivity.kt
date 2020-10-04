package com.teddybrothers.androidlearning.utils

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        if (itemId == android.R.id.home) // Press Back Icon
        {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}