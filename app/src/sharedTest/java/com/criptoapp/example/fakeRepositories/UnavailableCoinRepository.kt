package com.criptoapp.example.fakeRepositories

import com.example.criptoapp.data.repositories.CoinRepository
import com.example.criptoapp.presentation.details.CoinDetailsState
import com.example.criptoapp.presentation.coinList.CoinListState

class UnavailableCoinRepository : CoinRepository {
    override suspend fun getCoins(): CoinListState {
        return CoinListState.BackendError
    }

    override suspend fun getCoinDetailsById(coinId: String): CoinDetailsState {
        return CoinDetailsState.BackendError
    }
}