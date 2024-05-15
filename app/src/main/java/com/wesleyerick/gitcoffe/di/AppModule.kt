package com.wesleyerick.gitcoffe.di

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
//    viewModel { VehiclesViewModel(get()) }
}

val repositoryModule = module {
//    single<IRepositoryVehicles> { VehiclesRepository(get()) }
}

val useCaseModule = module {
//    factory { VehiclesUseCases(get(), get()) }

//    factory { StarshipsUseCases(get(), get()) }
}

val apiModule = module {
//    single { getRetrofitInstance() }
}