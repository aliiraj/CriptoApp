package com.example.criptoapp.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.criptoapp.R

@Composable
fun ShowError(
    @StringRes error: Int?,
    tryAgain: () -> Unit
) {
    error?.let {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = error))
            Spacer(modifier = Modifier.height(10.dp))
            TextButton(onClick = tryAgain) {
                Text(text = stringResource(R.string.try_again))
            }
        }
    }
}