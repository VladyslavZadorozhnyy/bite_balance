package com.benchmark_test

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.benchmark.macro.junit4.MacrobenchmarkRule

@RunWith(AndroidJUnit4::class)
class StartupBenchmarkTest {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()
    private val packageName = "com.bitebalance"
    private val iterationsCount = 5

    @Test
    fun startup() = benchmarkRule.measureRepeated(
        packageName = packageName,
        metrics = listOf(StartupTimingMetric()),
        iterations = iterationsCount,
        startupMode = StartupMode.COLD
    ) {
        pressHome()
        startActivityAndWait()
    }
}