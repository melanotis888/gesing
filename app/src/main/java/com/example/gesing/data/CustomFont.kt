package com.example.gesing.data

// Анотації Room для визначення сутностей бази даних
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Сутність бази даних, що представляє імпортований кастомний шрифт.
 *
 * @property id Унікальний ідентифікатор шрифту.
 * @property name Відображувана назва шрифту в інтерфейсі.
 * @property fileName Ім'я файлу шрифту у внутрішній пам'яті додатка.
 */
@Entity(tableName = "custom_fonts")
data class CustomFont(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val fileName: String
)
