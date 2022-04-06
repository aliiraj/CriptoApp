package com.example.criptoapp.presentation.details

import com.example.criptoapp.data.remote.dto.CoinDetailDto

sealed class CoinDetailsState{
    data class DataFetched(val coinDetailDto: CoinDetailDto): CoinDetailsState()
    object BackendError: CoinDetailsState()
    object OfflineError: CoinDetailsState()
    object Loading: CoinDetailsState()
}
