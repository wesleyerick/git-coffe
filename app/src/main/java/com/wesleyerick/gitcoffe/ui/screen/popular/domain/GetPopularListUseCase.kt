package com.wesleyerick.gitcoffe.ui.screen.popular.domain

import android.content.Context
import com.wesleyerick.gitcoffe.R
import com.wesleyerick.gitcoffe.ui.screen.popular.data.repository.IRepositoryPopular
import com.wesleyerick.gitcoffe.utils.safeRunDispatcher
import com.wesleyerick.gitcoffe.utils.Result

class GetPopularListUseCase(
    private val repository: IRepositoryPopular,
    private val context: Context
) {
    //    suspend operator fun invoke() = repository.getList()
    suspend operator fun invoke() = when (
        val result = safeRunDispatcher {
            repository.getList()
        }
    ) {
        is Result.Success -> {
            Result.Success(result.data.body())
        }

        is Result.Failure -> Result.Failure(result.error, context.getString(R.string.default_error_message))
    }
}