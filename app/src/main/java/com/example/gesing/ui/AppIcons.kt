package com.example.gesing.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Допоміжний об'єкт для роботи з іконками додатка.
 * Перетворює список іконок у карту для швидкого пошуку за назвою.
 */
object AppIcons {
    /**
     * Карта іконок: Ключ — назва, Значення — ImageVector.
     * Використовує lazy ініціалізацію для оптимізації.
     */
    val icons: Map<String, ImageVector> by lazy {
        allCustomIcons.toMap()
    }

    /**
     * Повертає об'єкт [ImageVector] за назвою іконки.
     * Якщо іконку не знайдено, повертає стандартну іконку 'Favorite'.
     *
     * @param name Назва іконки для пошуку.
     */
    fun getIcon(name: String): ImageVector {
        return icons[name] ?: Icons.Default.Favorite
    }
}
