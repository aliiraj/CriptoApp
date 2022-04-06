package com.example.criptoapp.presentation.details

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.criptoapp.example.CreateFakeData
import com.criptoapp.example.fakeRepositories.DelayingCoinRepository
import com.criptoapp.example.fakeRepositories.FakeCoinRepository
import com.criptoapp.example.fakeRepositories.OfflineCoinRepository
import com.criptoapp.example.fakeRepositories.UnavailableCoinRepository
import com.example.criptoapp.data.repositories.CoinRepository
import com.example.criptoapp.presentation.MainActivity
import com.example.criptoapp.presentation.coinList.launchCoinListScreenFor
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class CoinDetailsScreenTest {

    @get:Rule
    val coinDetailsScreenRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup(){
        replacePostCatalogWith(FakeCoinRepository(CreateFakeData.coinList))
        launchCoinListScreenFor(coinDetailsScreenRule){ tapOnListItem() }
    }

    @Test
    fun showLoadingIndicator() {
        replacePostCatalogWith(DelayingCoinRepository())
        launchCoinDetailsFor(coinDetailsScreenRule) {
            // no operation
        }.verify {
            loadingIndicatorIsDisplayed()
        }
    }

    @Test
    fun showSuccessDataLoaded() {
        replacePostCatalogWith(FakeCoinRepository(coinDetailDto = CreateFakeData.coinDetailDto))
        launchCoinDetailsFor(coinDetailsScreenRule) {
            // no operation
        }.verify {
            coinNameIsShown(CreateFakeData.coinDetailDto)
        }
    }

    @Test
    fun showOfflineError(){
        replacePostCatalogWith(OfflineCoinRepository())

        launchCoinDetailsFor(coinDetailsScreenRule){
            // no operation
        }.verify {
            errorOfflineIsVisible()
        }
    }

    @Test
    fun showServerError(){
        replacePostCatalogWith(UnavailableCoinRepository())

        launchCoinDetailsFor(coinDetailsScreenRule){
            // no operation
        }.verify {
            errorServerIsVisible()
        }
    }

    @Test
    fun onBackClick(){
        replacePostCatalogWith(UnavailableCoinRepository())
        launchCoinDetailsFor(coinDetailsScreenRule){
            clickOnBackIcon()
        }.verify {
            coinListScreenIsVisible()
        }
    }

    private fun replacePostCatalogWith(fakeCoinRepository: CoinRepository) {
        val replaceModule = module {
            single { fakeCoinRepository }
        }
        loadKoinModules(replaceModule)
    }

}