package com.wesleyerick.gitcoffe.api

import com.wesleyerick.gitcoffe.ui.screen.popular.data.service.PopularService
import com.wesleyerick.gitcoffe.ui.screen.pr.data.service.PullRequestService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


fun getRetrofitInstance(baseUrl: String): Retrofit {
    return Retrofit
        .Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun getPopularService(retrofit: Retrofit): PopularService =
    retrofit.create(PopularService::class.java)
fun getPullRequestService(retrofit: Retrofit): PullRequestService =
    retrofit.create(PullRequestService::class.java)