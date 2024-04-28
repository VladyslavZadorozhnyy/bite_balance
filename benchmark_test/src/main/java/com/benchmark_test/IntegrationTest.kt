package com.benchmark_test

import org.junit.Test
import android.util.Log
import org.junit.Before
import org.junit.runner.RunWith
import androidx.work.Configuration
import androidx.work.testing.SynchronousExecutor
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.work.testing.WorkManagerTestInitHelper
import androidx.test.platform.app.InstrumentationRegistry


@RunWith(AndroidJUnit4::class)
class IntegrationTest {
    @Before
    @Test
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val config = Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setExecutor(SynchronousExecutor())
            .build()

        WorkManagerTestInitHelper.initializeTestWorkManager(context, config)
    }
}