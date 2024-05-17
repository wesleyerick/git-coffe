package com.wesleyerick.gitcoffe.ui.screen.popular.domain

import com.wesleyerick.gitcoffe.ui.screen.popular.data.model.PopularRepositoriesItem
import com.wesleyerick.gitcoffe.ui.screen.popular.data.repository.IRepositoryPopular
import com.wesleyerick.gitcoffe.utils.Result
import com.wesleyerick.gitcoffe.utils.safeRunDispatcher

class GetPopularListUseCase(
    private val repository: IRepositoryPopular,
) {
    suspend operator fun invoke(page: Int): Result<List<PopularRepositoriesItem>> =
        when (
            val result = safeRunDispatcher {
                repository.getList(page)
            }
        ) {
            is Result.Success -> {
                val list = result.data.body()?.items ?: emptyList()
                Result.Success(list)
            }

            is Result.Failure -> Result.Failure(result.error)
        }
}