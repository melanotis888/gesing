package com.example.gesing.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

/**
 * Екран вибору іконок з підтримкою пошуку та пагінації.
 * Розбиває великий список іконок на сторінки по 50 штук для забезпечення плавності роботи.
 *
 * @param onIconSelected Коллбек, що викликається при виборі іконки (повертає її назву).
 */
@Composable
fun IconPickerScreen(
    onIconSelected: (String) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    
    // 1. Фільтрація за назвою
    // 2. Алфавітне сортування
    // 3. Розбиття на сторінки (чанки)
    val iconPages = remember(searchQuery) {
        allCustomIcons
            .filter { it.first.contains(searchQuery, ignoreCase = true) }
            .sortedBy { it.first }
            .chunked(50)
    }

    val pagerState = rememberPagerState(pageCount = { iconPages.size })

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(600.dp)
            .padding(8.dp)
    ) {
        // Поле пошуку
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search icons...") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { searchQuery = "" }) {
                        Icon(Icons.Default.Close, contentDescription = "Clear search")
                    }
                }
            }
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        if (iconPages.isEmpty()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text("No icons found matching \"$searchQuery\"")
            }
        } else {
            // Горизонтальний пейджер для перемикання сторінок
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f),
                beyondViewportPageCount = 1
            ) { pageIndex ->
                val pageIcons = iconPages[pageIndex]
                
                // Сітка іконок поточної сторінки
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 64.dp),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(pageIcons, key = { it.first }) { (name, icon) ->
                        IconGridItem(name = name, icon = icon) {
                            onIconSelected(name)
                        }
                    }
                }
            }
            
            // Індикатор поточної сторінки
            Text(
                text = "Page ${pagerState.currentPage + 1} of ${iconPages.size}",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

/**
 * Окремий елемент сітки вибору іконок.
 * Відображає іконку та її коротку назву знизу.
 */
@Composable
fun IconGridItem(
    name: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = name,
            modifier = Modifier.size(32.dp),
            tint = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = name,
            style = MaterialTheme.typography.labelSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 4.dp),
            textAlign = TextAlign.Center
        )
    }
}
