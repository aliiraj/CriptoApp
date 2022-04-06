package com.example.criptoapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.criptoapp.R
import com.example.criptoapp.data.remote.dto.CoinDto
import com.example.criptoapp.utils.Utils.getTitleCoinItem

@Composable
fun CoinListItem(
    item: CoinDto,
    onItemClick: (CoinDto) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(item) }
            .padding(20.dp),
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F),
            text = getTitleCoinItem(item),
            style = MaterialTheme.typography.body1,
            maxLines = 1,
            overflow = TextOverflow.Clip,
        )
        Text(
            text = if (item.isActive) stringResource(R.string.active) else stringResource(R.string.inactive),
            color = if (item.isActive) MaterialTheme.colors.primary else Color.Red,
        )
    }
}
