package com.example.gesing.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "custom_fonts")
data class CustomFont(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val fileName: String
)
