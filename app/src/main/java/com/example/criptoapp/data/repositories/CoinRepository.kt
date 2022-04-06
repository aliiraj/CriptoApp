package com.example.criptoapp.data.repositories

import com.example.criptoapp.presentation.details.CoinDetailsState
import com.example.criptoapp.presentation.coinList.CoinListState


interface CoinRepository {

    suspend fun getCoins(): CoinListState

    suspend fun getCoinDetailsById(coinId: String): CoinDetailsState
}