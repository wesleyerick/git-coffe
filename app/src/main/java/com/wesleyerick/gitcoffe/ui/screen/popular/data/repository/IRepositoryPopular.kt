package com.wesleyerick.gitcoffe.ui.screen.popular.data.repository

import retrofit2.Response

interface IRepositoryPopular {
    suspend fun getList() : Response<Any>
}