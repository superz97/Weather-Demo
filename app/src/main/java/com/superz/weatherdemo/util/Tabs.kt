package com.superz.weatherdemo.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

enum class Tabs(
    val title: String,
    val icon: ImageVector
) {
    HOME(
        title = "HOME",
        icon = Icons.Default.Home
    ),
    DAILY(
        title = "Daily",
        icon = Icons.Default.DateRange
    )
}