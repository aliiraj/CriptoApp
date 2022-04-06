package com.example.criptoapp.presentation.details

import com.criptoapp.example.CreateFakeData
import com.criptoapp.example.fakeRepositories.FakeCoinRepository
import com.criptoapp.example.fakeRepositories.OfflineCoinRepository
import com.criptoapp.example.TestDispatchers
import com.criptoapp.example.fakeRepositories.UnavailableCoinRepository
import com.example.criptoapp.InstantTaskExecutorExtension
import com.example.criptoapp.R
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith


@ExtendWith(InstantTaskExecutorExtension::class)
class CoinDetailsViewModelTest {

    @Test
    fun getCoinDetailsReturnBackendError(){
        val coinDetailsViewModel = CoinDetailsViewModel(
            UnavailableCoinRepository(),
            TestDispatchers()
        )

        coinDetailsViewModel.getCoinDetails(":irrelevant:")

        assertEquals(R.string.server_error, coinDetailsViewModel.screenState.value!!.error)
    }

    @Test
    fun getCoinDetailsReturnOfflineError(){
        val coinDetailsViewModel = CoinDetailsViewModel(
            OfflineCoinRepository(),
            TestDispatchers()
        )

        coinDetailsViewModel.getCoinDetails(":irrelevant:")

        assertEquals(R.string.check_connection, coinDetailsViewModel.screenState.value!!.error)
    }

    @Test
    fun getCoinDetailsReturnSuccessCoinDetails(){
        val fakeCoinRepository = FakeCoinRepository(coinDetailDto = CreateFakeData.coinDetailDto)
        val coinDetailsViewModel = CoinDetailsViewModel(
            fakeCoinRepository,
            TestDispatchers()
        )

        coinDetailsViewModel.getCoinDetails(":irrelevant:")

        assertEquals(CreateFakeData.coinDetailDto, coinDetailsViewModel.screenState.value!!.coinDetails)
    }

    @Test
    fun getCoinDetailsStatesInParticularOrderInSuccessState(){
        val fakeCoinRepository = FakeCoinRepository(coinDetailDto = CreateFakeData.coinDetailDto)
        val coinDetailsViewModel = CoinDetailsViewModel(
            fakeCoinRepository,
            TestDispatchers(),
        )

        val renderedStateList = mutableListOf<CoinDetailsScreenState>()
        coinDetailsViewModel.screenState.observeForever { renderedStateList.add(it) }

        coinDetailsViewModel.getCoinDetails(":irrelevant:")

        assertEquals(
            listOf(
                CoinDetailsScreenState(isLoading = true),
                CoinDetailsScreenState(isLoading = false, coinDetails = CreateFakeData.coinDetailDto)
            ),
            renderedStateList
        )
    }

    @Test
    fun getCoinDetailsStatesInParticularOrderInOfflineState(){
        val coinDetailsViewModel = CoinDetailsViewModel(
            OfflineCoinRepository(),
            TestDispatchers(),
        )

        val renderedStateList = mutableListOf<CoinDetailsScreenState>()
        coinDetailsViewModel.screenState.observeForever { renderedStateList.add(it) }

        coinDetailsViewModel.getCoinDetails(":irrelevant:")

        assertEquals(
            listOf(
                CoinDetailsScreenState(isLoading = true),
                CoinDetailsScreenState(isLoading = false, error = R.string.check_connection)
            ),
            renderedStateList
        )
    }

}