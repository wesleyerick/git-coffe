package com.wesleyerick.gitcoffe.ui.screen.pr.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wesleyerick.gitcoffe.ui.screen.pr.data.model.PullRequestItemResult
import com.wesleyerick.gitcoffe.ui.screen.pr.domain.GetPullRequestListUseCase
import com.wesleyerick.gitcoffe.utils.ResourceView
import com.wesleyerick.gitcoffe.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PullRequestViewModel(
    private val getPullRequestListUseCase: GetPullRequestListUseCase
) : ViewModel() {

    private val _state: MutableLiveData<ResourceView<List<PullRequestItemResult>>> =
        MutableLiveData(ResourceView.Loading)
    val state: LiveData<ResourceView<List<PullRequestItemResult>>> get() = _state
    fun getList(creator: String, repo: String) = viewModelScope.launch(Dispatchers.IO) {
        when (val result = getPullRequestListUseCase(creator, repo)) {
            is Result.Success -> _state.postValue(ResourceView.Success(result.data))
            is Result.Failure -> _state.postValue(ResourceView.Error(result.errorMessage))
        }
    }

}