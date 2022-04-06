package com.criptoapp.example

import com.example.criptoapp.data.remote.dto.CoinDetailDto
import com.example.criptoapp.data.remote.dto.CoinDto

object CreateFakeData {
    private val coinDto1 = CoinDto("1", true, true, "name1", 1, "", "")
    private val coinDto2 = CoinDto("2", true, true, "name2", 2, "", "")
    val coinList = mutableListOf(coinDto1, coinDto2)

    val coinDetailDto = CoinDetailDto(
        description = "description",
        id = "id",
        name = "name1",
        rank = 1,
        type = "active"
    )
}