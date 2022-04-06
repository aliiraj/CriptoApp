package com.example.criptoapp.presentation.details

import androidx.annotation.StringRes
import com.example.criptoapp.data.remote.dto.CoinDetailDto

data class CoinDetailsScreenState(
    val isLoading: Boolean = false,
    var coinDetails: CoinDetailDto? = null,
    @StringRes val error: Int? = null
)
