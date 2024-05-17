package com.wesleyerick.gitcoffe.di

import com.wesleyerick.gitcoffe.BuildConfig
import com.wesleyerick.gitcoffe.api.getPopularService
import com.wesleyerick.gitcoffe.api.getPullRequestService
import com.wesleyerick.gitcoffe.api.getRetrofitInstance
import com.wesleyerick.gitcoffe.ui.screen.popular.data.repository.IRepositoryPopular
import com.wesleyerick.gitcoffe.ui.screen.popular.data.repository.PopularRepository
import com.wesleyerick.gitcoffe.ui.screen.popular.domain.GetPopularListUseCase
import com.wesleyerick.gitcoffe.ui.screen.popular.presentation.PopularViewModel
import com.wesleyerick.gitcoffe.ui.screen.pr.data.repository.IRepositoryPullRequest
import com.wesleyerick.gitcoffe.ui.screen.pr.data.repository.PullRequestRepository
import com.wesleyerick.gitcoffe.ui.screen.pr.domain.GetPullRequestListUseCase
import com.wesleyerick.gitcoffe.ui.screen.pr.presentation.PullRequestViewModel
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
    viewModelOf(::PullRequestViewModel)
}

val repositoryModule = module {
    single<IRepositoryPopular> { PopularRepository(get()) }
    single<IRepositoryPullRequest> { PullRequestRepository(get()) }
}

val useCaseModule = module {
    factory { GetPopularListUseCase(get()) }
    factory { GetPullRequestListUseCase(get()) }
}

val apiModule = module {
    single { getRetrofitInstance(BuildConfig.BASE_URL) }
    factory { getPopularService(get()) }
    factory { getPullRequestService(get()) }
}