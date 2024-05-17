package com.wesleyerick.gitcoffe.ui.screen.popular.data.repository

import com.wesleyerick.gitcoffe.ui.screen.popular.data.model.PopularRepositoriesResult
import retrofit2.Response

interface IRepositoryPopular {
    suspend fun getList(page: Int) : Response<PopularRepositoriesResult>
}