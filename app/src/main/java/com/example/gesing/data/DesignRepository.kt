package com.example.gesing.data

import android.content.Context
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

/**
 * Репозиторій для управління даними дизайну.
 * Виконує роль посередника між DAO та бізнес-логікою (ViewModel).
 */
class DesignRepository(private val context: Context, private val designDao: DesignDao) {
    /**
     * Потік поточної конфігурації дизайну.
     */
    val config: Flow<DesignConfig?> = designDao.getConfig()

    /**
     * Потік списку імпортованих кастомних шрифтів.
     */
    val customFonts: Flow<List<CustomFont>> = designDao.getCustomFonts()

    /**
     * Зберігає змінену конфігурацію дизайну в базу даних.
     */
    suspend fun saveConfig(config: DesignConfig) {
        designDao.saveConfig(config)
    }

    /**
     * Імпортує файл шрифту з вказаного Uri у внутрішню пам'ять додатка.
     * Копіює файл у директорію 'fonts' та реєструє його в базі даних.
     *
     * @param uri Uri джерела файлу шрифту.
     * @param name Відображувана назва шрифту.
     */
    suspend fun importFont(uri: Uri, name: String) = withContext(Dispatchers.IO) {
        val fontsDir = File(context.filesDir, "fonts")
        if (!fontsDir.exists()) fontsDir.mkdirs()

        val fileName = "font_${System.currentTimeMillis()}.ttf"
        val destFile = File(fontsDir, fileName)

        context.contentResolver.openInputStream(uri)?.use { input ->
            FileOutputStream(destFile).use { output ->
                input.copyTo(output)
            }
        }

        designDao.insertCustomFont(CustomFont(name = name, fileName = fileName))
    }
}
