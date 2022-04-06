package com.criptoapp.example.fakeRepositories

import com.example.criptoapp.data.remote.dto.CoinDetailDto
import com.example.criptoapp.data.remote.dto.CoinDto
import com.example.criptoapp.data.repositories.CoinRepository
import com.example.criptoapp.presentation.details.CoinDetailsState
import com.example.criptoapp.presentation.coinList.CoinListState

class FakeCoinRepository(
    private val coinList: List<CoinDto> = emptyList(),
    private val coinDetailDto: CoinDetailDto? = null
) : CoinRepository {
    override suspend fun getCoins(): CoinListState {
        return CoinListState.DataFetched(coinList)
    }

    override suspend fun getCoinDetailsById(coinId: String): CoinDetailsState {
        return CoinDetailsState.DataFetched(coinDetailDto!!)
    }
}