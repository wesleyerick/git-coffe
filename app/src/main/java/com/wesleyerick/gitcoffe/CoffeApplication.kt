package com.wesleyerick.gitcoffe

import android.app.Application
import com.wesleyerick.gitcoffe.di.modulesList

import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class CoffeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() = startKoin {
        loadKoinModules(modulesList)
    }
}