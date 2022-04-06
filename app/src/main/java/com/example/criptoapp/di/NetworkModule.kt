package com.example.criptoapp.di

import com.example.criptoapp.BuildConfig
import com.example.criptoapp.data.remote.apis.CoinApi
import com.example.criptoapp.utils.Constants.BASE_URL
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single { GsonBuilder().create() }

    single {
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    single {
        OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) { addInterceptor(get() as HttpLoggingInterceptor) }
        }.build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { (get() as Retrofit).create(CoinApi::class.java) }

}