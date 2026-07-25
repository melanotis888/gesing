package com.example.gesing.ui

import android.content.Context
import android.net.Uri
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gesing.data.DesignConfig
import com.example.gesing.data.DesignRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.File

class DesignViewModel(
    private val repository: DesignRepository,
    private val context: Context
) : ViewModel() {

    val config: StateFlow<DesignConfig> = repository.config
        .filterNotNull()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DesignConfig()
        )

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

    fun updateConfig(newConfig: DesignConfig) {
        viewModelScope.launch {
            repository.saveConfig(newConfig)
        }
    }

    fun importFont(uri: Uri, name: String) {
        viewModelScope.launch {
            repository.importFont(uri, name)
        }
    }
}
