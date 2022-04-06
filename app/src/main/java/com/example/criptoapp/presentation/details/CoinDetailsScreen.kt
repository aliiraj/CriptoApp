package com.example.criptoapp.presentation.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.criptoapp.R
import com.example.criptoapp.data.remote.dto.CoinDetailDto
import com.example.criptoapp.data.remote.dto.TagDto
import com.example.criptoapp.data.remote.dto.TeamMemberDto
import com.example.criptoapp.presentation.components.*
import com.example.criptoapp.utils.Utils.getTitleCoinItem
import com.google.accompanist.flowlayout.FlowRow
import org.koin.androidx.compose.getViewModel

@Composable
fun CoinDetailsScreen(
    coinId: String,
    navController: NavController,
    coinDetailsVm: CoinDetailsViewModel = getViewModel()
) {
    val state = coinDetailsVm.screenState.observeAsState().value ?: CoinDetailsScreenState()
    if (shouldFetchCoinDetails(state)) { coinDetailsVm.getCoinDetails(coinId) }
    ShowCoinDetailsContent(state, coinDetailsVm, coinId, onBackPress = { navController.popBackStack() })
}

@Composable
fun ShowCoinDetailsContent(
    coinDetailsScreenState: CoinDetailsScreenState,
    coinDetailsVm: CoinDetailsViewModel,
    coinId: String,
    onBackPress: () -> Unit
) {
    Scaffold(
        topBar = { AppBarSection(onBackPress) },
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            ShowCoinDetails(coinDetailsScreenState.coinDetails)
            ShowError(
                error = coinDetailsScreenState.error,
                tryAgain = { coinDetailsVm.getCoinDetails(coinId) }
            )
            ShowLoading(coinDetailsScreenState.isLoading)
        }
    }
}

@Composable
fun ShowCoinDetails(coinDetailDto: CoinDetailDto?) {
    coinDetailDto?.let {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState())
                    .padding(24.dp)
            ) {
                HeaderDetails(coinDetailDto)
                Spacer(modifier = Modifier.height(15.dp))
                ExpandingText(text = coinDetailDto.description)
                Spacer(modifier = Modifier.height(15.dp))
                TagSection(coinDetailDto.tags)
                Spacer(modifier = Modifier.height(15.dp))
                TeamListSection(coinDetailDto.team)
            }
        }
    }
}

@Composable
fun TeamListSection(team: List<TeamMemberDto>?) {
    if (!team.isNullOrEmpty()){
        Column {
            Text(
                text = stringResource(R.string.team_members),
                style = MaterialTheme.typography.h6,
            )
            team.forEach { teamMember ->
                TeamListItem(
                    teamMember = teamMember,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
                Divider()
            }
        }
    }
}

@Composable
fun AppBarSection(onBackPress: () -> Unit){
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.coin_details)) },
        navigationIcon = {
            IconButton(
                modifier = Modifier.testTag(stringResource(id = R.string.back_tag)),
                onClick = onBackPress
            ){
                Icon(Icons.Default.ArrowBack,"back")
            }
        }
    )
}

@Composable
fun TagSection(tags: List<TagDto>?) {
    if (!tags.isNullOrEmpty()) {
        Column {
            Text(
                text = stringResource(R.string.tags),
                style = MaterialTheme.typography.h6,
            )
            Spacer(modifier = Modifier.height(10.dp))
            FlowRow(
                mainAxisSpacing = 10.dp,
                crossAxisSpacing = 10.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                tags.forEach { tag ->
                    CoinTag(tag = tag.name)
                }
            }
        }
    }
}

@Composable
fun HeaderDetails(coinDetailDto: CoinDetailDto) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F),
            text = getTitleCoinItem(coinDetailDto),
            style = MaterialTheme.typography.h6,
            maxLines = 1,
            overflow = TextOverflow.Clip,
        )
        coinDetailDto.isActive?.let {
            Text(
                text = getStatusTitle(coinDetailDto.isActive),
                color = getStatusTitleColor(coinDetailDto.isActive),
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.End,
            )
        }
    }
}

@Composable
private fun getStatusTitleColor(isActive: Boolean): Color {
    return if (isActive) MaterialTheme.colors.primary else Color.Red
}

@Composable
private fun getStatusTitle(isActive: Boolean): String {
    return if (isActive) stringResource(R.string.active) else stringResource(R.string.inactive)
}

private fun shouldFetchCoinDetails(state: CoinDetailsScreenState): Boolean {
    return !state.isLoading &&
            state.coinDetails == null &&
            state.error == null
}