package com.example.criptoapp.presentation.coinList

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
class CoinListViewModelTest {

    @Test
    fun getCoinListReturnBackendError() {
        val coinListViewModel = CoinListViewModel(
            UnavailableCoinRepository(),
            TestDispatchers()
        )

        coinListViewModel.getCoinList()

        assertEquals(R.string.server_error, coinListViewModel.screenState.value!!.error)
    }

    @Test
    fun getCoinListReturnOfflineError(){
        val coinListViewModel = CoinListViewModel(
            OfflineCoinRepository(),
            TestDispatchers()
        )

        coinListViewModel.getCoinList()

        assertEquals(R.string.check_connection, coinListViewModel.screenState.value!!.error)
    }

    @Test
    fun getCoinListReturnSuccessCoinList(){
        val coinListViewModel = CoinListViewModel(
            FakeCoinRepository(CreateFakeData.coinList),
            TestDispatchers()
        )

        coinListViewModel.getCoinList()

        assertEquals(CreateFakeData.coinList, coinListViewModel.screenState.value!!.coins)
    }

    @Test
    fun getCoinListStatesInParticularOrderInSuccessState(){
        val renderedStates = mutableListOf<CoinListScreenState>()
        val coinListViewModel = CoinListViewModel(
            FakeCoinRepository(CreateFakeData.coinList),
            TestDispatchers()
        )

        coinListViewModel.screenState.observeForever { renderedStates.add(it) }

        coinListViewModel.getCoinList()

        assertEquals(
            listOf(
                CoinListScreenState(isLoading = true),
                CoinListScreenState(isLoading = false, coins = CreateFakeData.coinList)
            ),
            renderedStates
        )
    }

    @Test
    fun getCoinListStatesInParticularOrderInOfflineState(){
        val renderedStates = mutableListOf<CoinListScreenState>()
        val coinListViewModel = CoinListViewModel(
            OfflineCoinRepository(),
            TestDispatchers()
        )

        coinListViewModel.screenState.observeForever { renderedStates.add(it) }

        coinListViewModel.getCoinList()

        assertEquals(
            listOf(
                CoinListScreenState(isLoading = true),
                CoinListScreenState(isLoading = false, error = R.string.check_connection)
            ),
            renderedStates
        )
    }
}
