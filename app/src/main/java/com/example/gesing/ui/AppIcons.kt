package com.example.gesing.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.graphics.vector.ImageVector

object AppIcons {
    // Use the user-provided list from IconsList.kt
    val icons: Map<String, ImageVector> by lazy {
        allCustomIcons.toMap()
    }

    fun getIcon(name: String): ImageVector {
        return icons[name] ?: Icons.Default.Favorite
    }
}
