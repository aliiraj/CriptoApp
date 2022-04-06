package com.example.criptoapp.di

import com.example.criptoapp.application.CoroutineDispatchers
import com.example.criptoapp.application.DefaultDispatchers
import com.example.criptoapp.data.remote.datasources.RemoteDataSource
import com.example.criptoapp.data.remote.datasources.RemoteDataSourceImpl
import com.example.criptoapp.data.repositories.CoinRepository
import com.example.criptoapp.data.repositories.CoinRepositoryImpl
import org.koin.dsl.module

val applicationModule = module {

    single<RemoteDataSource> {
        RemoteDataSourceImpl(coinApi = get())
    }

    single<CoroutineDispatchers> { DefaultDispatchers() }

    single<CoinRepository> {
        CoinRepositoryImpl(remoteDataSource = get())
    }

}