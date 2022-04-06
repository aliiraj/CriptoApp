package com.example.criptoapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import com.example.criptoapp.R

@Composable
fun ShowLoading(isShowing: Boolean) {
    if (isShowing) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .testTag(stringResource(id = R.string.loading))
                .background(MaterialTheme.colors.surface),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}