package com.example.criptoapp.data.repositories

import com.criptoapp.example.CreateFakeData
import com.criptoapp.example.fakeRemoteDataSources.FakeRemoteDataSource
import com.criptoapp.example.fakeRemoteDataSources.OfflineRemoteDataSource
import com.criptoapp.example.fakeRemoteDataSources.UnavailableRemoteDataSource
import com.example.criptoapp.presentation.coinList.CoinListState
import com.example.criptoapp.presentation.details.CoinDetailsState
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CoinRepositoryTest{

    @Test
    fun getCoinListReturnBackendError() = runBlocking {
        val repository = CoinRepositoryImpl(UnavailableRemoteDataSource())

        val coinListState = repository.getCoins()

        assertEquals(CoinListState.BackendError, coinListState)
    }

    @Test
    fun getCoinListReturnOfflineError() = runBlocking {
        val repository = CoinRepositoryImpl(OfflineRemoteDataSource())

        val coinListState = repository.getCoins()

        assertEquals(CoinListState.OfflineError, coinListState)
    }

    @Test
    fun getCoinListReturnSuccessCoinList() = runBlocking {
        val fakeRemoteDataSource = FakeRemoteDataSource(CreateFakeData.coinList)
        val repository = CoinRepositoryImpl(fakeRemoteDataSource)

        val coinListState = repository.getCoins()

        assertEquals(CoinListState.DataFetched(CreateFakeData.coinList), coinListState)
    }

    @Test
    fun getCoinDetailsReturnBackendError() = runBlocking {
        val repository = CoinRepositoryImpl(UnavailableRemoteDataSource())

        val coinDetailState = repository.getCoinDetailsById("")

        assertEquals(CoinDetailsState.BackendError, coinDetailState)
    }

    @Test
    fun getCoinDetailsReturnOfflineError() = runBlocking {
        val repository = CoinRepositoryImpl(OfflineRemoteDataSource())

        val coinDetailState = repository.getCoinDetailsById("")

        assertEquals(CoinDetailsState.OfflineError, coinDetailState)
    }

    @Test
    fun getCoinDetailsReturnSuccessCoinDetails() = runBlocking {
        val fakeRemoteDataSource = FakeRemoteDataSource(coinDetailDto = CreateFakeData.coinDetailDto)
        val repository = CoinRepositoryImpl(fakeRemoteDataSource)

        val coinDetailState = repository.getCoinDetailsById("")

        assertEquals(CoinDetailsState.DataFetched(CreateFakeData.coinDetailDto), coinDetailState)
    }
}