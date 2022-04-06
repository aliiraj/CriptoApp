package com.example.criptoapp.data.repositories

import com.example.criptoapp.data.remote.datasources.RemoteDataSource
import com.example.criptoapp.presentation.details.CoinDetailsState
import com.example.criptoapp.presentation.coinList.CoinListState
import com.example.criptoapp.utils.exceptions.BackendException
import com.example.criptoapp.utils.exceptions.OfflineException

class CoinRepositoryImpl constructor(
    private val remoteDataSource: RemoteDataSource,
) : CoinRepository {

    override suspend fun getCoins(): CoinListState {
        return try {
            val coins = remoteDataSource.getCoins()
            CoinListState.DataFetched(coins)
        } catch (e: BackendException) {
            CoinListState.BackendError
        } catch (e: OfflineException) {
            CoinListState.OfflineError
        }
    }

    override suspend fun getCoinDetailsById(coinId: String): CoinDetailsState {
        return try {
            val coinDetails = remoteDataSource.getCoinDetailsById(coinId)
            CoinDetailsState.DataFetched(coinDetails)
        } catch (e: BackendException) {
            CoinDetailsState.BackendError
        } catch (e: OfflineException) {
            CoinDetailsState.OfflineError
        }
    }
}