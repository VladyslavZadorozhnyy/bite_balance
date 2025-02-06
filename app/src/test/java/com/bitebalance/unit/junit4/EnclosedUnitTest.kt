package com.bitebalance.unit.junit4

import com.ui.basic.recycler_views.text_recycler.TextAdapter
import com.ui.basic.recycler_views.text_recycler.TextRecyclerModel
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.junit.Test

@RunWith(Enclosed::class)
class EnclosedUnitTest {
    class TextRecyclerModelUnitTest {
        @Test
        fun test() {
            println("TextRecyclerModelUnitTest, test()")
            TextRecyclerModel(
                items = listOf(),
                onClickListener = {},
            )
        }
    }

    class TextAdapterUnitTest {
        @Test
        fun test() {
            println("TextAdapterUnitTest, test()")
            TextAdapter(
                items = listOf(),
                onClickListener = {}
            )
        }
    }
}