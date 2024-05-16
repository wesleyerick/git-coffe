package com.wesleyerick.gitcoffe.ui.screen.popular.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wesleyerick.gitcoffe.ui.screen.popular.domain.GetPopularListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PopularViewModel(
    private val getPopularListUseCase: GetPopularListUseCase
) : ViewModel() {

    private val _getList: MutableLiveData<List<String>> =
        MutableLiveData(emptyList())
    val vehiclesList: LiveData<List<String>> get() = _getList

    init {
        getList()
    }

    private fun getList() = viewModelScope.launch(Dispatchers.IO) {
        val result = getPopularListUseCase()
        println("teste -> $result")
    }
}