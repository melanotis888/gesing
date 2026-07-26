package com.example.gesing.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Інтерфейс доступу до даних (DAO) для конфігурації дизайну та шрифтів.
 */
@Dao
interface DesignDao {
    /**
     * Отримує поточну конфігурацію дизайну з бази даних.
     * Повертає Flow для відстеження змін у реальному часі.
     */
    @Query("SELECT * FROM design_config WHERE id = 1")
    fun getConfig(): Flow<DesignConfig?>

    /**
     * Зберігає або оновлює конфігурацію дизайну.
     *
     * @param config Об'єкт конфігурації для збереження.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveConfig(config: DesignConfig)

    /**
     * Отримує список усіх імпортованих кастомних шрифтів.
     */
    @Query("SELECT * FROM custom_fonts")
    fun getCustomFonts(): Flow<List<CustomFont>>

    /**
     * Додає новий кастомний шрифт до бази даних.
     *
     * @param font Об'єкт метаданих шрифту.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomFont(font: CustomFont)
}
