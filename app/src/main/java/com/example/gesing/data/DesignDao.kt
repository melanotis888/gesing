package com.example.gesing.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DesignDao {
    @Query("SELECT * FROM design_config WHERE id = 1")
    fun getConfig(): Flow<DesignConfig?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveConfig(config: DesignConfig)

    @Query("SELECT * FROM custom_fonts")
    fun getCustomFonts(): Flow<List<CustomFont>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomFont(font: CustomFont)
}
