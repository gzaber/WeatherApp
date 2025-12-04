package com.gzaber.weatherapp.ui.util.composable

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable

@Composable
fun ErrorSnackbar(
    snackbarHostState: SnackbarHostState
) {
    SnackbarHost(hostState = snackbarHostState)
}