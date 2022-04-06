package com.example.criptoapp.data.remote.datasources

import com.criptoapp.example.CreateFakeData
import com.example.criptoapp.data.remote.apis.CoinApi
import com.example.criptoapp.utils.exceptions.BackendException
import com.example.criptoapp.utils.exceptions.OfflineException
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.function.Executable
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


class RemoteDataSourceImplTest {
    private val coinApi: CoinApi = mockk()
    private val remoteDataSourceImpl: RemoteDataSource = RemoteDataSourceImpl(coinApi)

    @Test
    fun getCoinListReturnOfflineException(): Unit = runBlocking {
        coEvery { coinApi.getCoins() } throws IOException()

        assertThrows<OfflineException>{ remoteDataSourceImpl.getCoins() }
    }

    @Test
    fun getCoinListReturnBackendException(): Unit = runBlocking {
        coEvery { coinApi.getCoins() } throws HttpException(Response.success(null))

        assertThrows<BackendException>{ remoteDataSourceImpl.getCoins() }
    }

    @Test
    fun getCoinListReturnSuccessCoinList(): Unit = runBlocking {
        coEvery { coinApi.getCoins() } returns CreateFakeData.coinList

        val response = remoteDataSourceImpl.getCoins()

        assertEquals(CreateFakeData.coinList, response)
    }

    @Test
    fun getCoinDetailsReturnOfflineException(): Unit = runBlocking {
        coEvery { coinApi.getCoinDetailsById("") } throws IOException()

        assertThrows<OfflineException>{ remoteDataSourceImpl.getCoinDetailsById("") }
    }

    @Test
    fun getCoinDetailsReturnBackendException(): Unit = runBlocking {
        coEvery { coinApi.getCoinDetailsById("") } throws HttpException(Response.success(null))

        assertThrows<BackendException>{ remoteDataSourceImpl.getCoinDetailsById("") }
    }

    @Test
    fun getCoinDetailsReturnSuccessCoinDetails(): Unit = runBlocking {
        coEvery { coinApi.getCoinDetailsById("") } returns CreateFakeData.coinDetailDto

        val response = remoteDataSourceImpl.getCoinDetailsById("")

        assertEquals(CreateFakeData.coinDetailDto, response)
    }
}