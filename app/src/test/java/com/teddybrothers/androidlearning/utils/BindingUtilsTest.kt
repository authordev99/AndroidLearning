package com.teddybrothers.androidlearning.utils

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.teddybrothers.androidlearning.utils.BindingUtils.toDp
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class BindingUtilsTest {

    private lateinit var context: Context

    @Before
    fun setup() {
        context = InstrumentationRegistry.getInstrumentation().context
    }

    @Test
    fun test_setMovieDuration() {
        val runtime = 144
        val duration = BindingUtils.setMovieDuration(runtime).toString()
        assertNotNull(duration)
        assertEquals("2h 24min", duration)
    }

    @Test
    fun test_toDp() {
        val valueToDp = 4.toDp(context)
        assertNotNull(valueToDp)
        assertTrue(valueToDp > 0)
    }
}