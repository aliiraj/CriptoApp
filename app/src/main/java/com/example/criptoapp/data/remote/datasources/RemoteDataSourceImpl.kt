package com.example.criptoapp.data.remote.datasources

import com.example.criptoapp.utils.exceptions.OfflineException
import com.example.criptoapp.data.remote.apis.CoinApi
import com.example.criptoapp.data.remote.dto.CoinDetailDto
import com.example.criptoapp.data.remote.dto.CoinDto
import com.example.criptoapp.utils.exceptions.BackendException
import retrofit2.HttpException
import java.io.IOException

class RemoteDataSourceImpl constructor(
    private val coinApi: CoinApi
) : RemoteDataSource {

    override suspend fun getCoins(): List<CoinDto> {
        return try {
            coinApi.getCoins()
        } catch (e: HttpException) {
            throw BackendException()
        } catch (e: IOException) {
            throw OfflineException()
        }
    }

    override suspend fun getCoinDetailsById(coinId: String): CoinDetailDto {
        return try {
            coinApi.getCoinDetailsById(coinId)
        } catch (e: HttpException) {
            throw BackendException()
        } catch (e: IOException) {
            throw OfflineException()
        }
    }

}