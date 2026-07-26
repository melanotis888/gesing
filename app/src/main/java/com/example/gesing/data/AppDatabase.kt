package com.example.gesing.data

// Контекст Android для ініціалізації бази даних
import android.content.Context

// Компоненти Room для роботи з локальною БД
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Основний клас бази даних Room для додатка Gesing.
 * Використовується для зберігання конфігурації дизайну та списку кастомних шрифтів.
 */
@Database(entities = [DesignConfig::class, CustomFont::class], version = 5, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    /**
     * Надає доступ до методів маніпуляції даними дизайну.
     */
    abstract val designDao: DesignDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Повертає екземпляр бази даних (Singleton). Створює новий, якщо він ще не існує.
         *
         * @param context Контекст додатка для ініціалізації Room.
         */
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "gesing_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
