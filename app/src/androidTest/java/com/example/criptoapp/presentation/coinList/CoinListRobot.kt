package com.example.criptoapp.presentation.coinList

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.criptoapp.R
import com.example.criptoapp.data.remote.dto.CoinDto
import com.example.criptoapp.presentation.MainActivity
import com.example.criptoapp.utils.Utils.getTitleCoinItem


typealias MainActivityRule = AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>

fun launchCoinListScreenFor(
    screenRule: MainActivityRule,
    block: CoinListRobot.() -> Unit
): CoinListRobot {
    return CoinListRobot(screenRule).apply { block() }
}

class CoinListRobot(private val screenRule: MainActivityRule) {

    fun tapOnListItem() {
        screenRule.onNodeWithTag(screenRule.activity.getString(R.string.lazy_column_tag))
            .onChildren()
            .onFirst()
            .performClick()
    }

    infix fun verify(
        block: CoinListVerificationRobot.() -> Unit
    ): CoinListVerificationRobot {
        return CoinListVerificationRobot(screenRule).apply(block)
    }
}

class CoinListVerificationRobot(
    private val rule: MainActivityRule
) {

    fun loadingIndicatorIsDisplayed() {
        val loading = rule.activity.getString(R.string.loading)
        rule.onNodeWithTag(loading)
            .assertIsDisplayed()
    }

    fun coinsAreAvailable(coinList: MutableList<CoinDto>) {
        coinList.forEach { item ->
            rule.onNodeWithText(getTitleCoinItem(item))
                .assertIsDisplayed()
        }
    }

    fun errorOfflineIsVisible(){
        val error = rule.activity.getString(R.string.check_connection)
        rule.onNodeWithText(error)
            .assertIsDisplayed()
    }

    fun errorServerIsVisible(){
        val error = rule.activity.getString(R.string.server_error)
        rule.onNodeWithText(error)
            .assertIsDisplayed()
    }

}