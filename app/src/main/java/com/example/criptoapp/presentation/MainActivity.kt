package com.example.criptoapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.criptoapp.presentation.details.CoinDetailsScreen
import com.example.criptoapp.presentation.coinList.CoinListScreen
import com.example.criptoapp.presentation.navigation.Screen
import com.example.criptoapp.ui.theme.CriptoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CriptoAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainApp()
                }
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.CoinList.route
    ){
        composable(Screen.CoinList.route){
            CoinListScreen(navController)
        }
        composable(Screen.CoinDetails.route){ backStackEntry ->
            CoinDetailsScreen(
                coinId = backStackEntry.arguments?.getString(Screen.CoinDetails.coinId) ?: "",
                navController = navController
            )
        }
    }
}