package com.wesleyerick.gitcoffe.ui.screen.popular.data.service

import retrofit2.Response
import retrofit2.http.GET

interface PopularService {

    @GET("search/repositories?q=language:Java&sort=stars&page=l")
    suspend fun getList(): Response<Any>
}