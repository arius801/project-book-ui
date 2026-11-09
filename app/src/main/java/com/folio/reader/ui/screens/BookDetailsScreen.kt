package com.folio.reader.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.folio.reader.ui.Book
import com.folio.reader.ui.components.BookCover
import com.folio.reader.ui.components.FolioButton
import com.folio.reader.ui.components.SectionTitle
import androidx.compose.ui.tooling.preview.Preview
import com.folio.reader.ui.sampleBooks
import com.folio.reader.ui.theme.FolioTheme

@Composable
fun BookDetailsScreen(
    book: Book,
    onBack: () -> Unit,
    onRead: () -> Unit,
    onAi: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        TextButton(onClick = onBack) { Text("‹  Назад") }
        Spacer(Modifier.height(8.dp))
        BookCover(book, Modifier.size(width = 128.dp, height = 164.dp).align(Alignment.CenterHorizontally))
        Spacer(Modifier.height(18.dp))
        Text(book.title, modifier = Modifier.fillMaxWidth(), fontSize = 23.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        Text(book.author, modifier = Modifier.fillMaxWidth(), fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant, textAlign = TextAlign.Center)
        Spacer(Modifier.height(12.dp))
        Text("★  4.8     ·     ${book.pages} страниц     ·     ~6 ч чтения", modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.primary, fontSize = 11.sp, textAlign = TextAlign.Center)
        Spacer(Modifier.height(20.dp))
        FolioButton("Начать чтение  →", onRead, Modifier.fillMaxWidth())
        Spacer(Modifier.height(14.dp))
        Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
            Column(Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(" ✦", color = MaterialTheme.colorScheme.primary, fontSize = 22.sp)
                    Spacer(Modifier.size(10.dp))
                    Column {
                        Text("Краткое описание", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        Text("Главные идеи за 2 минуты", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 10.sp)
                    }
                }
                Spacer(Modifier.height(12.dp))
                Text(
                    "Маленькие привычки, повторяемые каждый день, создают большие изменения. Начните с простого: сделайте полезное действие очевидным.",
                    fontSize = 13.sp,
                    lineHeight = 19.sp
                )
                Spacer(Modifier.height(10.dp))
                TextButton(onClick = onAi) { Text("Открыть результат AI") }
            }
        }
        Spacer(Modifier.height(20.dp))
        SectionTitle("Метрики книги")
        Spacer(Modifier.height(10.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Metric("ЖАНР", book.genre, Modifier.weight(1f))
            Metric("ТЕМП", "Практичный", Modifier.weight(1f))
        }
        Spacer(Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Metric("ЭМОЦИЯ", "Вдохновляющий", Modifier.weight(1f))
            Metric("СЛОЖНОСТЬ", "Доступная", Modifier.weight(1f))
        }
    }
}

@Composable
private fun Metric(label: String, value: String, modifier: Modifier = Modifier) {
    Card(modifier = modifier, colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
        Column(Modifier.padding(12.dp)) {
            Text(label, fontSize = 9.sp, color = MaterialTheme.colorScheme.onSurfaceVariant, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(4.dp))
            Text(value, fontSize = 12.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Preview(name = "Book details - Light", showBackground = true, showSystemUi = true)
@Composable
private fun BookDetailsLightPreview() {
    FolioTheme(darkMode = false) {
        BookDetailsScreen(book = sampleBooks.first(), onBack = {}, onRead = {}, onAi = {})
    }
}