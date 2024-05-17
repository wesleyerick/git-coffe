package com.wesleyerick.gitcoffe.ui.screen.popular.data.repository

import com.wesleyerick.gitcoffe.ui.screen.popular.data.model.PopularRepositoriesResult
import com.wesleyerick.gitcoffe.ui.screen.popular.data.service.PopularService
import retrofit2.Response

class PopularRepository(private val service: PopularService) : IRepositoryPopular {
    override suspend fun getList(page: Int): Response<PopularRepositoriesResult> =
        service.getList(page)
}