package com.example.criptoapp.data.remote.datasources

import com.example.criptoapp.data.remote.dto.CoinDetailDto
import com.example.criptoapp.data.remote.dto.CoinDto

interface RemoteDataSource {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinDetailsById(coinId: String): CoinDetailDto

}