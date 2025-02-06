package com.bitebalance.unit.junit4

import android.graphics.Color
import com.ui.basic.recycler_views.metric_recycler.MetricRecyclerModel
import org.junit.Test
import org.junit.experimental.categories.Category

@Category(MetricTests::class)
class MetricUnitTest {
    @Test
    fun test_metric_recycler_model() {
        println("test_metric_recycler_model")
        MetricRecyclerModel(
            items = listOf(),
            foregroundColor = Color.BLACK,
            backgroundColor = Color.WHITE,
        )
    }
}