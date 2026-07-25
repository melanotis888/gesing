package com.example.gesing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gesing.data.AppDatabase
import com.example.gesing.data.DesignRepository
import com.example.gesing.ui.DesignViewModel
import com.example.gesing.ui.DesignViewModelFactory
import com.example.gesing.ui.NoteListScreen
import com.example.gesing.ui.SettingsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val database = AppDatabase.getDatabase(this)
        val repository = DesignRepository(this, database.designDao)
        val factory = DesignViewModelFactory(repository, this)
        val viewModel = ViewModelProvider(this, factory)[DesignViewModel::class.java]

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    
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
