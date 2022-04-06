package com.criptoapp.example.fakeRemoteDataSources

import com.example.criptoapp.data.remote.datasources.RemoteDataSource
import com.example.criptoapp.data.remote.dto.CoinDetailDto
import com.example.criptoapp.data.remote.dto.CoinDto
import com.example.criptoapp.utils.exceptions.BackendException

class UnavailableRemoteDataSource : RemoteDataSource {
    override suspend fun getCoins(): List<CoinDto> {
        throw BackendException()
    }

    override suspend fun getCoinDetailsById(coinId: String): CoinDetailDto {
        throw BackendException()
    }
}