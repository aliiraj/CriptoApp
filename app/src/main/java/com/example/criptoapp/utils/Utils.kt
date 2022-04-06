package com.example.criptoapp.utils

import com.example.criptoapp.data.remote.dto.CoinDetailDto
import com.example.criptoapp.data.remote.dto.CoinDto

object Utils {

    fun getTitleCoinItem(item: Any): String {
        return when(item){
            is CoinDetailDto -> "${item.rank}. ${item.name} (${item.type})"
            is CoinDto -> "${item.rank}. ${item.name} (${item.type})"
            else -> ""
        }
    }

}