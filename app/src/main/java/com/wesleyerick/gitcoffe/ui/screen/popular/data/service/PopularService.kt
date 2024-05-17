package com.wesleyerick.gitcoffe.ui.screen.popular.data.service

import com.wesleyerick.gitcoffe.ui.screen.popular.data.model.PopularRepositoriesResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PopularService {

    @GET("search/repositories?q=language:Java&sort=stars&")
    suspend fun getList(@Query("page") page: Int): Response<PopularRepositoriesResult>
}