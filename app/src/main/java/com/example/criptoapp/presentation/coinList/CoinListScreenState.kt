package com.example.criptoapp.presentation.coinList

import androidx.annotation.StringRes
import com.example.criptoapp.data.remote.dto.CoinDto

data class CoinListScreenState(
    val isLoading: Boolean = false,
    var coins: List<CoinDto>? = null,
    @StringRes val error: Int? = null
)
