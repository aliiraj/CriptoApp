package com.criptoapp.example.fakeRepositories

import com.example.criptoapp.data.repositories.CoinRepository
import com.example.criptoapp.presentation.details.CoinDetailsState
import com.example.criptoapp.presentation.coinList.CoinListState
import kotlinx.coroutines.delay


class DelayingCoinRepository : CoinRepository {
    override suspend fun getCoins(): CoinListState {
        delay(2000)
        // return is not matter
        return CoinListState.OfflineError
    }

    override suspend fun getCoinDetailsById(coinId: String): CoinDetailsState {
        delay(2000)
        // return is not matter
        return CoinDetailsState.OfflineError
    }
}