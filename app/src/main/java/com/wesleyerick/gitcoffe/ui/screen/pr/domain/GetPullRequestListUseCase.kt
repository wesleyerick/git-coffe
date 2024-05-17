package com.wesleyerick.gitcoffe.ui.screen.pr.domain

import com.wesleyerick.gitcoffe.ui.screen.pr.data.repository.IRepositoryPullRequest
import com.wesleyerick.gitcoffe.utils.Result
import com.wesleyerick.gitcoffe.utils.safeRunDispatcher


class GetPullRequestListUseCase(
    private val repository: IRepositoryPullRequest
) {
    suspend operator fun invoke(creator: String, repo: String) =
        when (
            val result = safeRunDispatcher {
                repository.getList(creator, repo)
            }
        ) {
            is Result.Success -> {
                val list = result.data.body() ?: emptyList()
                Result.Success(list)
            }

            is Result.Failure -> Result.Failure(result.error)
        }
}