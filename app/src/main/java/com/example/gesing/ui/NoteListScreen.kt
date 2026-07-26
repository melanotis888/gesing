package com.example.gesing.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.gesing.data.DesignConfig

/**
 * Екран попереднього перегляду дизайну.
 * Відображає, як вибрані налаштування (кольори, шрифти, фон) виглядають на реальному контенті.
 *
 * @param viewModel ViewModel з поточними налаштуваннями дизайну.
 * @param onBack Коллбек для повернення на екран налаштувань.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteListScreen(
    viewModel: DesignViewModel,
    onBack: () -> Unit
) {
    val config by viewModel.config.collectAsState()
    val allFonts by viewModel.allFonts.collectAsState()
    
    // Підготовка параметрів кольору та шрифту
    val textColor = Color(config.textR, config.textG, config.textB)
    val bgColor = Color(config.bgR, config.bgG, config.bgB)
    val fontFamily = allFonts.find { it.first == config.fontFamily }?.second ?: FontFamily.Default

    val iconColor = Color(config.iconR, config.iconG, config.iconB)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Preview", fontFamily = fontFamily) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = if (config.backgroundImageUri != null) Color.Transparent else bgColor,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            // Відображення фонового зображення або суцільного кольору
            if (config.backgroundImageUri != null) {
                AsyncImage(
                    model = config.backgroundImageUri,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.tint(bgColor, BlendMode.Modulate)
                )
            } else {
                Box(modifier = Modifier.fillMaxSize().background(bgColor))
            }

            // Приклад списку нотаток для демонстрації тексту
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val demoNotes = listOf("Meeting notes")
                items(demoNotes) { note ->
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = if (config.backgroundImageUri != null) 
                                Color.Black.copy(alpha = 0.4f) 
                            else bgColor.copy(alpha = 0.9f)
                        ),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Text(
                            text = note,
                            modifier = Modifier.padding(16.dp).fillMaxWidth(),
                            color = textColor,
                            fontSize = config.fontSize.sp,
                            fontFamily = fontFamily
                        )
                    }
                }
            }

            // Демонстрація плаваючої кнопки з вибраною іконкою
            IconButton(
                onClick = { /* Action */ },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(24.dp)
                    .size(72.dp)
                    .background(
                        color = iconColor.copy(alpha = 0.8f),
                        shape = CircleShape
                    )
            ) {
                Icon(
                    imageVector = AppIcons.getIcon(config.iconName),
                    contentDescription = "Action Icon",
                    modifier = Modifier.size(40.dp),
                    tint = if (iconColor.luminance() > 0.5f) Color.Black else Color.White
                )
            }
        }
    }
}
