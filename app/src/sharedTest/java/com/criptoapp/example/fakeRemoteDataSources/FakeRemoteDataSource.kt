package com.criptoapp.example.fakeRemoteDataSources

import com.example.criptoapp.data.remote.datasources.RemoteDataSource
import com.example.criptoapp.data.remote.dto.CoinDetailDto
import com.example.criptoapp.data.remote.dto.CoinDto

class FakeRemoteDataSource(
    private val coinList: List<CoinDto> = emptyList(),
    private val coinDetailDto: CoinDetailDto? = null
) : RemoteDataSource {
    override suspend fun getCoins(): List<CoinDto> {
        return coinList
    }

    override suspend fun getCoinDetailsById(coinId: String): CoinDetailDto {
        return coinDetailDto!!
    }
}