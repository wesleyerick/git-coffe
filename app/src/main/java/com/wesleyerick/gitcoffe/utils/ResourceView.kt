package com.wesleyerick.gitcoffe.utils

sealed class ResourceView<out T> {
    data class Success<out T>(val data: T) : ResourceView<T>()
    data class Error(val errorMessage: String) : ResourceView<Nothing>()
    data object Loading : ResourceView<Nothing>()
}