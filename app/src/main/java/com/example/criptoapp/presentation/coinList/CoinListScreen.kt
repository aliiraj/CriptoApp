package com.example.criptoapp.presentation.coinList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.criptoapp.R
import com.example.criptoapp.data.remote.dto.CoinDto
import com.example.criptoapp.presentation.components.CoinListItem
import com.example.criptoapp.presentation.components.ShowError
import com.example.criptoapp.presentation.components.ShowLoading
import com.example.criptoapp.presentation.navigation.Screen
import org.koin.androidx.compose.getViewModel

@Composable
fun CoinListScreen(
    navController: NavController,
    coinListVm: CoinListViewModel = getViewModel()
) {
    val coinListState = coinListVm.screenState.observeAsState().value ?: CoinListScreenState()
    if (shouldFetchCoinList(coinListState)) { coinListVm.getCoinList() }
    CoinListContent(coinListState, navController, coinListVm)
}

@Composable
fun CoinListContent(
    coinListState: CoinListScreenState,
    navController: NavController,
    coinListVm: CoinListViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) })
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()){
            ShowCoinList(coinListState.coins, navController)
            ShowError(
                error = coinListState.error,
                tryAgain = { coinListVm.getCoinList() }
            )
            ShowLoading(coinListState.isLoading)
        }
    }
}

@Composable
fun ShowCoinList(
    coins: List<CoinDto>?,
    navController: NavController
) {
    coins?.let {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (coins.isEmpty()){
                Text(text = stringResource(id = R.string.no_coins_found))
            } else {
                LazyColumn(modifier = Modifier.testTag(stringResource(id = R.string.lazy_column_tag))) {
                    items(coins) { item ->
                        CoinListItem(item) { navController.navigate(Screen.CoinDetails.createRoute(it.id)) }
                        Divider()
                    }
                }
            }
        }
    }
}

private fun shouldFetchCoinList(coinListState: CoinListScreenState): Boolean {
    return !coinListState.isLoading &&
            coinListState.coins.isNullOrEmpty() &&
            coinListState.error == null
}
