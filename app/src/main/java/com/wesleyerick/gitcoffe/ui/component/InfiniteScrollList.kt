package com.wesleyerick.gitcoffe.ui.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag


const val InfiniteScrollListTag = "InfiniteScrollList"
@Composable
fun InfiniteScrollList(
    itemCount: Int,
    loadMoreItems: () -> Unit,
    content: @Composable (Int) -> Unit
) {
    val listState = rememberLazyListState()

    LazyColumn(state = listState, modifier = Modifier.semantics {
        contentDescription = InfiniteScrollListTag
        testTag = InfiniteScrollListTag
    }) {
        items(itemCount) { index ->
            content(index)
            if (index == itemCount - 1) {
                loadMoreItems()
            }
        }
    }
}