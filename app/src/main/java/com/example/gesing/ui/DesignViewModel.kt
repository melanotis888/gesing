package com.example.gesing.ui

// Контекст Android та робота з Uri (для доступу до файлів)
import android.content.Context
import android.net.Uri

// Jetpack Compose для роботи зі шрифтами та оформленням
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily

// Android Lifecycle для реалізації ViewModel та Coroutine Scope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

// Внутрішні сутності та репозиторій для роботи з даними
import com.example.gesing.data.DesignConfig
import com.example.gesing.data.DesignRepository

// Kotlin Coroutines для реактивного програмування та потоків
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

// Робота з файловою системою Java
import java.io.File

/**
 * ViewModel для управління станом дизайну додатка.
 * Забезпечує доступ до конфігурації, списку шрифтів та методів їх оновлення.
 */
class DesignViewModel(
    private val repository: DesignRepository,
    private val context: Context
) : ViewModel() {

    /**
     * Поточна конфігурація дизайну, отримана з репозиторію.
     */
    val config: StateFlow<DesignConfig> = repository.config
        .filterNotNull()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DesignConfig()
        )

    /**
     * Повний список доступних шрифтів, включаючи системні та кастомні.
     * Кастомні шрифти завантажуються динамічно з файлової системи.
     */
    val allFonts: StateFlow<List<Pair<String, FontFamily>>> = repository.customFonts
        .map { customList ->
            val dynamicFonts = customList.map { cf ->
                val file = File(context.filesDir, "fonts/${cf.fileName}")
                cf.name to FontFamily(Font(file))
            }
            AppFonts + dynamicFonts
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = AppFonts
        )

    /**
     * Оновлює конфігурацію дизайну в базі даних.
     */
    fun updateConfig(newConfig: DesignConfig) {
        viewModelScope.launch {
            repository.saveConfig(newConfig)
        }
    }

    /**
     * Імпортує новий файл шрифту.
     *
     * @param uri Uri джерела файлу.
     * @param name Назва шрифту.
     */
    fun importFont(uri: Uri, name: String) {
        viewModelScope.launch {
            repository.importFont(uri, name)
        }
    }
}
