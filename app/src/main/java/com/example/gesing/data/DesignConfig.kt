package com.example.gesing.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Сутність бази даних для зберігання глобальних налаштувань дизайну додатка.
 *
 * @property id Ідентифікатор конфігурації (завжди 1).
 * @property fontSize Розмір шрифту в sp.
 * @property fontFamily Назва вибраної родини шрифтів.
 * @property textR Червоний канал кольору тексту.
 * @property textG Зелений канал кольору тексту.
 * @property textB Синій канал кольору тексту.
 * @property bgR Червоний канал кольору фону.
 * @property bgG Зелений канал кольору фону.
 * @property bgB Синій канал кольору фону.
 * @property backgroundImageUri Uri фонового зображення (якщо встановлено).
 * @property iconName Назва вибраної іконки для кнопки дії.
 * @property iconR Червоний канал кольору іконки.
 * @property iconG Зелений канал кольору іконки.
 * @property iconB Синій канал кольору іконки.
 */
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
