package com.example.criptoapp.application

import android.app.Application
import com.example.criptoapp.di.applicationModule
import com.example.criptoapp.di.networkModule
import com.example.criptoapp.di.viewModelModule
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        handleCoinModules()
    }

    private fun handleCoinModules() {
        startKoin {
            modules(applicationModule, viewModelModule, networkModule)
        }
    }

}