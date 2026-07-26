package com.example.gesing.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gesing.data.DesignRepository

/**
 * Фабрика для створення екземпляра [DesignViewModel].
 * Дозволяє передавати необхідні залежності через конструктор.
 */
class DesignViewModelFactory(
    private val repository: DesignRepository,
    private val context: Context
) : ViewModelProvider.Factory {
    /**
     * Створює новий екземпляр вказаного класу ViewModel.
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DesignViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DesignViewModel(repository, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
