package com.wesleyerick.gitcoffe.di

import com.wesleyerick.gitcoffe.BuildConfig
import com.wesleyerick.gitcoffe.api.getPopularService
import com.wesleyerick.gitcoffe.api.getPullRequestService
import com.wesleyerick.gitcoffe.api.getRetrofitInstance
import com.wesleyerick.gitcoffe.ui.screen.popular.data.repository.IRepositoryPopular
import com.wesleyerick.gitcoffe.ui.screen.popular.data.repository.PopularRepository
import com.wesleyerick.gitcoffe.ui.screen.popular.domain.GetPopularListUseCase
import com.wesleyerick.gitcoffe.ui.screen.popular.presentation.PopularViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val modulesList by lazy {
    listOf(
        viewModelModule,
        repositoryModule,
        useCaseModule,
        apiModule,
    )
}

val viewModelModule = module {
    viewModelOf(::PopularViewModel)
}

val repositoryModule = module {
    single<IRepositoryPopular> { PopularRepository(get()) }
}

val useCaseModule = module {
    factory { GetPopularListUseCase(get()) }

//    factory { StarshipsUseCases(get(), get()) }
}

val apiModule = module {
    single { getRetrofitInstance(BuildConfig.BASE_URL) }
    factory { getPopularService(get()) }
    factory { getPullRequestService(get()) }
}