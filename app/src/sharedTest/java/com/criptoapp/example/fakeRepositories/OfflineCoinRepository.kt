package com.criptoapp.example.fakeRepositories

import com.example.criptoapp.data.repositories.CoinRepository
import com.example.criptoapp.presentation.details.CoinDetailsState
import com.example.criptoapp.presentation.coinList.CoinListState

class OfflineCoinRepository : CoinRepository {
    override suspend fun getCoins(): CoinListState {
        return CoinListState.OfflineError
    }

    override suspend fun getCoinDetailsById(coinId: String): CoinDetailsState {
        return CoinDetailsState.OfflineError
    }
}