package com.slava_110.compayments.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

val DarkTheme = darkColorScheme(

)

@Composable
fun AppTheme(content: @Composable () -> Unit) =
    MaterialTheme(
        colorScheme = DarkTheme,
        content = content
    )