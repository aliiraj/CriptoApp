package com.example.criptoapp.presentation.details

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.criptoapp.R
import com.example.criptoapp.data.remote.dto.CoinDetailDto
import com.example.criptoapp.presentation.MainActivity
import com.example.criptoapp.utils.Utils.getTitleCoinItem

typealias MainActivityRule = AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>

fun launchCoinDetailsFor(
    screenRule: MainActivityRule,
    block: CoinDetailsRobot.() -> Unit
): CoinDetailsRobot {
    return CoinDetailsRobot(screenRule).apply{ block() }
}

class CoinDetailsRobot(
    private val screenRule: MainActivityRule
){
    infix fun verify(
        block: CoinDetailsVerificationRobot.() -> Unit
    ): CoinDetailsVerificationRobot {
        return CoinDetailsVerificationRobot(screenRule).apply(block)
    }

    fun clickOnBackIcon() {
        screenRule.onNodeWithTag(screenRule.activity.getString(R.string.back_tag))
            .performClick()
    }
}

class CoinDetailsVerificationRobot(private val rule: MainActivityRule){
    fun loadingIndicatorIsDisplayed() {
        val loading = rule.activity.getString(R.string.loading)
        rule.onNodeWithTag(loading)
            .assertIsDisplayed()
    }

    fun coinNameIsShown(item: CoinDetailDto) {
        rule.onNodeWithText(getTitleCoinItem(item))
            .assertIsDisplayed()
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

    fun coinListScreenIsVisible() {
        val toolbarTitle = rule.activity.getString(R.string.app_name)
        rule.onNodeWithText(toolbarTitle)
            .assertIsDisplayed()
    }

}