package com.example.criptoapp.presentation.navigation

sealed class Screen(val route: String) {

  object CoinList : Screen("coinList")

  object CoinDetails : Screen("coinDetails/{coinId}") {
    const val coinId = "coinId"
    fun createRoute(coinId: String) = "coinDetails/$coinId"
  }

}