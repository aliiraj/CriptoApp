package com.example.criptoapp.data.remote.apis

import com.example.criptoapp.data.remote.dto.CoinDetailDto
import com.example.criptoapp.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinApi {

    @GET("coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("coins/{coinId}")
    suspend fun getCoinDetailsById(
        @Path("coinId") coinId: String
    ): CoinDetailDto

}