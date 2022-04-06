package com.example.criptoapp.presentation.coinList

import com.example.criptoapp.data.remote.dto.CoinDto

sealed class CoinListState{
    data class DataFetched(val coins: List<CoinDto>): CoinListState()
    object BackendError: CoinListState()
    object OfflineError: CoinListState()
    object Loading: CoinListState()
}
