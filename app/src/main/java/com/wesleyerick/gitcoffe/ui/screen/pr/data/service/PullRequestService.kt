package com.wesleyerick.gitcoffe.ui.screen.pr.data.service

import com.wesleyerick.gitcoffe.ui.screen.pr.data.model.PullRequestItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PullRequestService {

    @GET("repos/{creator}/{repo}/pulls")
    suspend fun getList(
        @Path("creator") creator: String,
        @Path("repo") repo: String
    ): Response<List<PullRequestItem>>
}