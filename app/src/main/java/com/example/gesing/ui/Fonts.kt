package com.example.gesing.ui

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.gesing.R

/**
 * Список стандартних шрифтів, які постачаються разом із додатком.
 * Містить як вбудовані системні шрифти, так і локальні ресурси.
 */
val AppFonts = listOf(
    "Sans" to FontFamily.SansSerif,
    "Serif" to FontFamily.Serif,
    "Monospace" to FontFamily.Monospace,
    "Cursive" to FontFamily.Cursive,
    "Font 1" to FontFamily(Font(R.font.font1)),
    "Font 2" to FontFamily(Font(R.font.font2))
)
