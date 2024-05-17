package com.wesleyerick.gitcoffe.ui.screen.pr.data.repository

import com.wesleyerick.gitcoffe.ui.screen.pr.data.model.PullRequestItem
import com.wesleyerick.gitcoffe.ui.screen.pr.data.service.PullRequestService
import retrofit2.Response

class PullRequestRepository(
    private val pullRequestService: PullRequestService
) : IRepositoryPullRequest {
    override suspend fun getList(creator: String, repo: String): Response<List<PullRequestItem>> =
        pullRequestService.getList(
            creator = creator,
            repo = repo
        )
}