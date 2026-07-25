package com.example.gesing.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "design_config")
data class DesignConfig(
    @PrimaryKey val id: Int = 1,
    val fontSize: Float = 16f,
    val fontFamily: String = "Sans",
    // Text Color RGB
    val textR: Int = 0,
    val textG: Int = 0,
    val textB: Int = 0,
    // Background Color RGB
    val bgR: Int = 255,
    val bgG: Int = 255,
    val bgB: Int = 255,
    val backgroundImageUri: String? = null,
    val iconName: String = "Favorite",
    // Button Icon Color RGB (Default to Black for visibility)
    val iconR: Int = 0,
    val iconG: Int = 0,
    val iconB: Int = 0
)
