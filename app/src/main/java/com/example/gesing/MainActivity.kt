package com.example.gesing

// Базові класи Android для роботи з активностями та станом додатка
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

// Jetpack Compose UI для створення розмітки та оформлення
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier

// Класи для роботи з ViewModel та ін'єкцією залежностей
import androidx.lifecycle.ViewModelProvider

// Компоненти навігації Jetpack Compose
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// Рівень даних: база даних та репозиторій
import com.example.gesing.data.AppDatabase
import com.example.gesing.data.DesignRepository

// Рівень інтерфейсу: ViewModel, фабрика та екрани
import com.example.gesing.ui.DesignViewModel
import com.example.gesing.ui.DesignViewModelFactory
import com.example.gesing.ui.NoteListScreen
import com.example.gesing.ui.SettingsScreen

/**
 * Головна активність додатка, яка слугує точкою входу.
 * Налаштовує базу даних, репозиторій, ViewModel та систему навігації Jetpack Compose.
 */
class MainActivity : ComponentActivity() {
    /**
     * Викликається під час створення активності. Ініціалізує компоненти додатка та встановлює вміст інтерфейсу.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Ініціалізація бази даних та репозиторію
        val database = AppDatabase.getDatabase(this)
        val repository = DesignRepository(this, database.designDao)

        // Створення фабрики ViewModel для передачі залежностей
        val factory = DesignViewModelFactory(repository, this)
        val viewModel = ViewModelProvider(this, factory)[DesignViewModel::class.java]

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    
                    // Налаштування навігації між екраном налаштувань та переглядом
                    NavHost(navController = navController, startDestination = "settings") {
                        composable("settings") {
                            SettingsScreen(
                                viewModel = viewModel,
                                onPreviewClick = { navController.navigate("preview") }
                            )
                        }
                        composable("preview") {
                            NoteListScreen(
                                viewModel = viewModel,
                                onBack = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}
