package com.example.criptoapp.presentation.coinList

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.criptoapp.example.CreateFakeData
import com.criptoapp.example.fakeRepositories.DelayingCoinRepository
import com.criptoapp.example.fakeRepositories.FakeCoinRepository
import com.criptoapp.example.fakeRepositories.OfflineCoinRepository
import com.criptoapp.example.fakeRepositories.UnavailableCoinRepository
import com.example.criptoapp.data.repositories.CoinRepository
import com.example.criptoapp.presentation.MainActivity
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class CoinListScreenTest {

    @get:Rule
    val coinListScreenRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun showLoadingIndicator() {
        replacePostCatalogWith(DelayingCoinRepository())
        launchCoinListScreenFor(coinListScreenRule) {
            // no operation
        }.verify {
            loadingIndicatorIsDisplayed()
        }
    }

    @Test
    fun showSuccessDataLoaded() {
        replacePostCatalogWith(FakeCoinRepository(CreateFakeData.coinList))

        launchCoinListScreenFor(coinListScreenRule) {
            // no operation
        }.verify {
            coinsAreAvailable(CreateFakeData.coinList)
        }
    }

    @Test
    fun showOfflineError(){
        replacePostCatalogWith(OfflineCoinRepository())

        launchCoinListScreenFor(coinListScreenRule){

        }.verify {
            errorOfflineIsVisible()
        }
    }

    @Test
    fun showServerError(){
        replacePostCatalogWith(UnavailableCoinRepository())

        launchCoinListScreenFor(coinListScreenRule){

        }.verify {
            errorServerIsVisible()
        }
    }

    private fun replacePostCatalogWith(fakeCoinRepository: CoinRepository) {
        val replaceModule = module {
            single { fakeCoinRepository }
        }
        loadKoinModules(replaceModule)
    }

}