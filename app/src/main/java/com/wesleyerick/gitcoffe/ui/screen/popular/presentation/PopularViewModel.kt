package com.wesleyerick.gitcoffe.ui.screen.popular.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wesleyerick.gitcoffe.ui.screen.popular.data.model.PopularRepositoriesItem
import com.wesleyerick.gitcoffe.ui.screen.popular.domain.GetPopularListUseCase
import com.wesleyerick.gitcoffe.utils.ResourceView
import com.wesleyerick.gitcoffe.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PopularViewModel(
    private val getPopularListUseCase: GetPopularListUseCase
) : ViewModel() {

    private val _state: MutableLiveData<ResourceView<List<PopularRepositoriesItem>>> =
        MutableLiveData(ResourceView.Loading)
    val state: LiveData<ResourceView<List<PopularRepositoriesItem>>> get() = _state

    private var page = 1
    private var cachedList = mutableListOf<PopularRepositoriesItem>()

    init {
        getList()
    }

    fun getList() = viewModelScope.launch(Dispatchers.IO) {
        when (val result = getPopularListUseCase(page)) {
            is Result.Success -> {
                page++
                cachedList.addAll(result.data)
                _state.postValue(ResourceView.Success(cachedList.toList()))
            }

            is Result.Failure -> _state.postValue(ResourceView.Error(result.errorMessage))
        }
    }
}