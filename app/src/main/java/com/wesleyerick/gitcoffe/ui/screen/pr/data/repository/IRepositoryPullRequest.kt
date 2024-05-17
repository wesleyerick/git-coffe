package com.wesleyerick.gitcoffe.ui.screen.pr.data.repository

import com.wesleyerick.gitcoffe.ui.screen.pr.data.model.PullRequestItemResult
import retrofit2.Response

interface IRepositoryPullRequest {
    suspend fun getList(creator: String, repo: String) : Response<List<PullRequestItemResult>>
}