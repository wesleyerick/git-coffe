package com.wesleyerick.gitcoffe.ui.component

import androidx.compose.material3.Text
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

class InifiniteScrollInstrumentedTest {

    companion object {
        const val mockText = "Teste"
    }

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun infiniteScrollShowingData() {
        rule.apply {
            setContent {
                InfiniteScrollList(
                    itemCount = 10,
                    loadMoreItems = {

                    }
                ) {
                    Text(text = mockText)
                }
            }
        }
        rule.onNodeWithTag(InfiniteScrollListTag).assertIsDisplayed()
    }
}