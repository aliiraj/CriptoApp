package com.example.criptoapp.presentation.details

import androidx.lifecycle.*
import com.example.criptoapp.R
import com.example.criptoapp.application.CoroutineDispatchers
import com.example.criptoapp.data.repositories.CoinRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoinDetailsViewModel constructor(
    private val coinRepository: CoinRepository,
    private val dispatchers: CoroutineDispatchers,
) : ViewModel(){

    var screenState = MutableLiveData<CoinDetailsScreenState>()
        private set

    fun getCoinDetails(coinId: String) {
        viewModelScope.launch {
            updateScreenState(CoinDetailsState.Loading)
            val result = withContext(dispatchers.background){
                coinRepository.getCoinDetailsById(coinId)
            }
            updateScreenState(result)
        }
    }

    private fun updateScreenState(coinDetailsState: CoinDetailsState) {
        val currentState = screenState.value ?: CoinDetailsScreenState()
        val newState = when (coinDetailsState) {
            is CoinDetailsState.Loading ->
                currentState.copy(isLoading = true)
            is CoinDetailsState.DataFetched ->
                currentState.copy(isLoading = false, coinDetails = coinDetailsState.coinDetailDto, error = null)
            is CoinDetailsState.BackendError ->
                currentState.copy(isLoading = false, error = R.string.server_error)
            is CoinDetailsState.OfflineError ->
                currentState.copy(isLoading = false, error = R.string.check_connection)
        }
        screenState.value = newState
    }
}