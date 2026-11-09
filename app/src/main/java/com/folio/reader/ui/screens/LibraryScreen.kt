package com.folio.reader.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.folio.reader.ui.Book
import com.folio.reader.ui.sampleBooks
import com.folio.reader.ui.components.BookCover
import com.folio.reader.ui.components.BookGridCard
import com.folio.reader.ui.components.FolioButton
import com.folio.reader.ui.components.SectionTitle
import androidx.compose.ui.tooling.preview.Preview
import com.folio.reader.ui.theme.FolioTheme

@Composable
fun LibraryScreen(
    onBookClick: (Book) -> Unit,
    onContinue: () -> Unit
) {
    var search by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("Все") }
    var showFileDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 14.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text("  ", color = MaterialTheme.colorScheme.primary, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(5.dp))
                Text("Твоя библиотека", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                Text("Истории, к которым хочется вернуться", fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            FolioButton(text = "+   Файл", onClick = { showFileDialog = true }, height = 36.dp, fontSize = 14.sp)
        }

        Spacer(Modifier.height(18.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            shape = MaterialTheme.shapes.large
        ) {
            Row(Modifier.padding(horizontal = 14.dp, vertical = 13.dp), verticalAlignment = Alignment.CenterVertically) {
                Text("⌕", fontSize = 22.sp, color = MaterialTheme.colorScheme.primary)
                Spacer(Modifier.width(10.dp))
                BasicTextField(
                    value = search,
                    onValueChange = { search = it },
                    singleLine = true,
                    textStyle = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurface),
                    decorationBox = { inner ->
                        if (search.isEmpty()) Text("Найти книгу или автора", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 12.sp)
                        inner()
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(Modifier.height(10.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            listOf("Все", "Читаю", "В планах", "Прочитано").forEach { filter ->
                FilterChip(
                    selected = selectedFilter == filter,
                    onClick = { selectedFilter = filter },
                    label = { Text(filter, fontSize = 10.sp) }
                )
            }
        }

        Spacer(Modifier.height(18.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            SectionTitle("Продолжить чтение", Modifier.weight(1f))
            Text("68%", color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold, fontSize = 12.sp)
        }
        Spacer(Modifier.height(9.dp))
        ContinueCard(book = sampleBooks[0], onClick = onContinue)

        Spacer(Modifier.height(22.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            SectionTitle("Твоя полка", Modifier.weight(1f))
            Text("Все книги  ↗", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 11.sp)
        }
        Spacer(Modifier.height(10.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            BookGridCard(sampleBooks[0], onClick = { onBookClick(sampleBooks[0]) }, modifier = Modifier.weight(1f))
            BookGridCard(sampleBooks[1], onClick = { onBookClick(sampleBooks[1]) }, modifier = Modifier.weight(1f))
        }
        Spacer(Modifier.height(12.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            BookGridCard(sampleBooks[2], onClick = { onBookClick(sampleBooks[2]) }, modifier = Modifier.weight(1f))
            Spacer(Modifier.weight(1f))
        }
        Spacer(Modifier.height(24.dp))
    }

    if (showFileDialog) {
        AlertDialog(
            onDismissRequest = { showFileDialog = false },
            title = { Text("Добавить книгу") },
            text = { Text("Это демонстрация интерфейса. Выберите пример книги, чтобы посмотреть следующие экраны.") },
            confirmButton = {
                TextButton(onClick = { showFileDialog = false; onBookClick(sampleBooks[0]) }) {
                    Text("Выбрать пример")
                }
            },
            dismissButton = {
                TextButton(onClick = { showFileDialog = false }) { Text("Отмена") }
            }
        )
    }
}

@Composable
private fun ContinueCard(book: Book, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.extraLarge,
        colors = CardDefaults.cardColors(containerColor = com.folio.reader.ui.theme.FolioColors.DeepGreen)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BookCover(book, Modifier.width(94.dp).height(132.dp))
            Spacer(Modifier.width(13.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(book.title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                Text(book.author, color = Color.White.copy(alpha = 0.75f), fontSize = 11.sp)
                Spacer(Modifier.height(12.dp))
                Text("ГЛАВА 08", color = Color(0xFFD7EF72), fontSize = 9.sp, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Прочитано", color = Color.White.copy(alpha = 0.75f), fontSize = 10.sp, modifier = Modifier.weight(1f))
                    Text("${book.progress}%", color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(Modifier.height(5.dp))
                androidx.compose.material3.LinearProgressIndicator(
                    progress = { book.progress / 100f },
                    modifier = Modifier.fillMaxWidth().height(5.dp),
                    color = Color(0xFFD7EF72),
                    trackColor = Color.White.copy(alpha = 0.25f)
                )
                Spacer(Modifier.height(10.dp))
                Text("Продолжить  →", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp)
            }
        }
    }
}


@Preview(name = "Library - Light", showBackground = true, showSystemUi = true)
@Composable
private fun LibraryScreenLightPreview() {
    FolioTheme(darkMode = false) {
        LibraryScreen(onBookClick = {}, onContinue = {})
    }
}
