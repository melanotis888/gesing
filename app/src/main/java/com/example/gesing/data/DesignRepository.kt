package com.example.gesing.data

import android.content.Context
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

class DesignRepository(private val context: Context, private val designDao: DesignDao) {
    val config: Flow<DesignConfig?> = designDao.getConfig()
    val customFonts: Flow<List<CustomFont>> = designDao.getCustomFonts()

    suspend fun saveConfig(config: DesignConfig) {
        designDao.saveConfig(config)
    }

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
