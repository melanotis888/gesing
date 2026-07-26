package com.example.gesing.ui

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.gesing.data.DesignConfig

/**
 * Головний екран налаштувань дизайну.
 * Дозволяє користувачу змінювати розмір шрифту, кольори тексту, кнопок та фону, вибирати іконки та імпортувати шрифти.
 *
 * @param viewModel ViewModel для управління станом конфігурації.
 * @param onPreviewClick Коллбек для переходу до екрана попереднього перегляду.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: DesignViewModel,
    onPreviewClick: () -> Unit
) {
    val config by viewModel.config.collectAsState()
    val allFonts by viewModel.allFonts.collectAsState()
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    var showIconPicker by remember { mutableStateOf(false) }
    var showFontPicker by remember { mutableStateOf(false) }

    // Лаунчер для вибору зображення з галереї
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (uri != null) {
                // Отримання постійного дозволу на читання файлу
                val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
                context.contentResolver.takePersistableUriPermission(uri, flag)
                viewModel.updateConfig(config.copy(backgroundImageUri = uri.toString()))
            }
        }
    )

    // Лаунчер для вибору файлу шрифту (.ttf, .otf)
    val fontPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri ->
            if (uri != null) {
                // Визначення імені файлу для відображення
                val fileName = context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
                    val nameIndex = cursor.getColumnIndex(android.provider.OpenableColumns.DISPLAY_NAME)
                    cursor.moveToFirst()
                    cursor.getString(nameIndex)
                } ?: "Custom Font"
                viewModel.importFont(uri, fileName)
            }
        }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Design Settings") },
                actions = {
                    IconButton(onClick = onPreviewClick) {
                        Icon(Icons.Default.Visibility, contentDescription = "Preview")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            // Налаштування розміру шрифту
            Text("Font Size: ${config.fontSize.toInt()}")
            Slider(
                value = config.fontSize,
                onValueChange = { viewModel.updateConfig(config.copy(fontSize = it)) },
                valueRange = 5f..105f
            )

            Spacer(modifier = Modifier.height(16.dp))
            // Налаштування кольору тексту
            Text("Text Color (RGB)")
            ColorSliders(
                r = config.textR,
                g = config.textG,
                b = config.textB,
                onColorChange = { r, g, b ->
                    viewModel.updateConfig(config.copy(textR = r, textG = g, textB = b))
                }
            )

            Spacer(modifier = Modifier.height(16.dp))
            // Вибір родини шрифтів
            Text("Font Family")
            val currentFontFamily = allFonts.find { it.first == config.fontFamily }?.second ?: FontFamily.Default
            OutlinedButton(
                onClick = { showFontPicker = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = config.fontFamily,
                    fontFamily = currentFontFamily
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            // Вибір фонового зображення
            Text("Background Image")
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = {
                        photoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.Image, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Pick Image")
                }
                if (config.backgroundImageUri != null) {
                    Button(
                        onClick = { viewModel.updateConfig(config.copy(backgroundImageUri = null)) },
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                    ) {
                        Icon(Icons.Default.Delete, contentDescription = "Clear background")
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            // Налаштування іконки кнопки
            Text("Button Icon")
            IconButton(
                onClick = { showIconPicker = true },
                modifier = Modifier.size(64.dp)
            ) {
                Icon(
                    imageVector = AppIcons.getIcon(config.iconName),
                    contentDescription = "Change Icon",
                    modifier = Modifier.size(48.dp),
                    tint = Color(config.iconR, config.iconG, config.iconB)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            // Налаштування кольору іконки
            Text("Button Color (RGB)")
            ColorSliders(
                r = config.iconR,
                g = config.iconG,
                b = config.iconB,
                onColorChange = { r, g, b ->
                    viewModel.updateConfig(config.copy(iconR = r, iconG = g, iconB = b))
                }
            )

            Spacer(modifier = Modifier.height(16.dp))
            // Налаштування кольору фону
            Text("Background Color (RGB)")
            ColorSliders(
                r = config.bgR,
                g = config.bgG,
                b = config.bgB,
                onColorChange = { r, g, b ->
                    viewModel.updateConfig(config.copy(bgR = r, bgG = g, bgB = b))
                }
            )
        }
    }

    // Діалог вибору іконки
    if (showIconPicker) {
        AlertDialog(
            onDismissRequest = { showIconPicker = false },
            title = { Text("Select Icon") },
            text = {
                IconPickerScreen(onIconSelected = { name ->
                    viewModel.updateConfig(config.copy(iconName = name))
                    showIconPicker = false
                })
            },
            confirmButton = {
                TextButton(onClick = { showIconPicker = false }) {
                    Text("Cancel")
                }
            }
        )
    }

    // Діалог вибору шрифту з можливістю додавання нових
    if (showFontPicker) {
        AlertDialog(
            onDismissRequest = { showFontPicker = false },
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Select Font")
                    IconButton(onClick = {
                        fontPickerLauncher.launch(arrayOf("*/*"))
                    }) {
                        Icon(Icons.Default.Add, contentDescription = "Add Font")
                    }
                }
            },
            text = {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth().heightIn(max = 400.dp)
                ) {
                    items(allFonts) { (name, family) ->
                        ListItem(
                            headlineContent = { Text(name, fontFamily = family) },
                            modifier = Modifier.clickable {
                                viewModel.updateConfig(config.copy(fontFamily = name))
                                showFontPicker = false
                            },
                            trailingContent = {
                                if (config.fontFamily == name) {
                                    Icon(Icons.Default.Check, contentDescription = null)
                                }
                            }
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showFontPicker = false }) {
                    Text("Close")
                }
            }
        )
    }
}

/**
 * Набір повзунків (RGB) для вибору кольору.
 * Кожен повзунок розфарбований у відповідний колір каналу.
 *
 * @param r Поточне значення червоного каналу.
 * @param g Поточне значення зеленого каналу.
 * @param b Поточне значення синього каналу.
 * @param onColorChange Коллбек, що повертає оновлені значення R, G, B.
 */
@Composable
fun ColorSliders(
    r: Int,
    g: Int,
    b: Int,
    onColorChange: (Int, Int, Int) -> Unit
) {
    Column {
        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            Text("R", modifier = Modifier.width(20.dp))
            Slider(
                value = r.toFloat(),
                onValueChange = { onColorChange(it.toInt(), g, b) },
                valueRange = 0f..255f,
                modifier = Modifier.weight(1f),
                colors = SliderDefaults.colors(
                    thumbColor = Color.Red,
                    activeTrackColor = Color.Red,
                    inactiveTrackColor = Color.Red.copy(alpha = 0.24f)
                )
            )
        }
        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            Text("G", modifier = Modifier.width(20.dp))
            Slider(
                value = g.toFloat(),
                onValueChange = { onColorChange(r, it.toInt(), b) },
                valueRange = 0f..255f,
                modifier = Modifier.weight(1f),
                colors = SliderDefaults.colors(
                    thumbColor = Color.Green,
                    activeTrackColor = Color.Green,
                    inactiveTrackColor = Color.Green.copy(alpha = 0.24f)
                )
            )
        }
        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            Text("B", modifier = Modifier.width(20.dp))
            Slider(
                value = b.toFloat(),
                onValueChange = { onColorChange(r, g, it.toInt()) },
                valueRange = 0f..255f,
                modifier = Modifier.weight(1f),
                colors = SliderDefaults.colors(
                    thumbColor = Color.Blue,
                    activeTrackColor = Color.Blue,
                    inactiveTrackColor = Color.Blue.copy(alpha = 0.24f)
                )
            )
        }
    }
}
