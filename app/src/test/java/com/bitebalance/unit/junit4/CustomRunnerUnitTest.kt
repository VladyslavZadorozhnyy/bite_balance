package com.bitebalance.unit.junit4

import android.graphics.Color
import com.ui.common.Constants
import com.ui.components.graph.component.GraphModel
import com.ui.model.NutritionValueModel
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.BlockJUnit4ClassRunner
import org.junit.runners.model.FrameworkMethod
import org.junit.runners.model.Statement


class CustomRunner(clazz: Class<Any>) : BlockJUnit4ClassRunner(clazz) {
    override fun methodBlock(method: FrameworkMethod): Statement? {
        println("Running test: " + method.name)
        return super.methodBlock(method)
    }
}

@RunWith(CustomRunner::class)
class CustomRunnerUnitTest {
    @Test
    fun custom_runner_test() {
        GraphModel(
            consumption = listOf(),
            consumptionGoal = NutritionValueModel(0F, 0F, 0F, 0F),
            foregroundColor = Color.BLACK,
            backgroundColor = Color.WHITE,
            screenSpan = Constants.TEXT_SIZE,
        )
    }
}