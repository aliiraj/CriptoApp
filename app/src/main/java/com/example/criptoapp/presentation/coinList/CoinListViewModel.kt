package com.example.criptoapp.presentation.coinList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.criptoapp.R
import com.example.criptoapp.application.CoroutineDispatchers
import com.example.criptoapp.data.repositories.CoinRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoinListViewModel constructor(
    private val coinRepository: CoinRepository,
    private val dispatchers: CoroutineDispatchers
) : ViewModel(){

    var screenState = MutableLiveData<CoinListScreenState>()
        private set

    fun getCoinList(){
        viewModelScope.launch {
            updateScreenState(CoinListState.Loading)
            val result = withContext(dispatchers.background){
                coinRepository.getCoins()
            }
            updateScreenState(result)
        }
    }

    private fun updateScreenState(coinListState: CoinListState) {
        val currentState = screenState.value ?: CoinListScreenState()
        val newState = when (coinListState) {
            is CoinListState.Loading ->
                currentState.copy(isLoading = true)
            is CoinListState.DataFetched ->
                currentState.copy(isLoading = false, coins = coinListState.coins, error = null)
            is CoinListState.BackendError ->
                currentState.copy(isLoading = false, error = R.string.server_error)
            is CoinListState.OfflineError ->
                currentState.copy(isLoading = false, error = R.string.check_connection)
        }
        screenState.value = newState
    }

}