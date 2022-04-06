package com.example.criptoapp.di

import com.example.criptoapp.presentation.details.CoinDetailsViewModel
import com.example.criptoapp.presentation.coinList.CoinListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        CoinListViewModel(
            coinRepository = get(),
            dispatchers = get()
        )
    }

    viewModel {
        CoinDetailsViewModel(
            coinRepository = get(),
            dispatchers = get()
        )
    }

}