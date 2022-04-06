package com.criptoapp.example.fakeRemoteDataSources

import com.example.criptoapp.data.remote.datasources.RemoteDataSource
import com.example.criptoapp.data.remote.dto.CoinDetailDto
import com.example.criptoapp.data.remote.dto.CoinDto
import com.example.criptoapp.utils.exceptions.OfflineException

class OfflineRemoteDataSource : RemoteDataSource {
    override suspend fun getCoins(): List<CoinDto> {
        throw OfflineException()
    }

    override suspend fun getCoinDetailsById(coinId: String): CoinDetailDto {
        throw OfflineException()
    }
}