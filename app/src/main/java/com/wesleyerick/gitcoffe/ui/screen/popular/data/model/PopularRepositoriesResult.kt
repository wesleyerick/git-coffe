package com.wesleyerick.gitcoffe.ui.screen.popular.data.model

data class PopularRepositoriesResult(
    val incomplete_results: Boolean = true,
    val items: List<PopularRepositoriesItem> = emptyList(),
    val total_count: Int = 0
)