package com.folio.reader.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.folio.reader.ui.components.FolioButton
import com.folio.reader.ui.components.SectionTitle
import androidx.compose.ui.tooling.preview.Preview
import com.folio.reader.ui.theme.FolioTheme

@Composable
fun AiLoadingScreen(onResult: () -> Unit, onError: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text("АНАЛИЗ КНИГИ", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        SectionTitle("Атомные привычки")
        Text("Джеймс Клир", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 12.sp)
        Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
            Column(Modifier.fillMaxWidth().padding(18.dp)) {
                Text("✦  Формируем описание…", fontWeight = FontWeight.Bold)
                Text("Анализируем содержание книги и готовим краткую аннотацию.", fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(Modifier.height(25.dp))
                androidx.compose.material3.LinearProgressIndicator(Modifier.fillMaxWidth())
                Spacer(Modifier.height(16.dp))
                repeat(3) {
                    androidx.compose.material3.LinearProgressIndicator(
                        progress = { 0.72f - it * 0.12f },
                        modifier = Modifier.fillMaxWidth().height(7.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                    Spacer(Modifier.height(12.dp))
                }
            }
        }
        Spacer(Modifier.height(4.dp))
        FolioButton("Показать результат", onResult, Modifier.fillMaxWidth())
        FolioButton("Показать ошибку", onError, Modifier.fillMaxWidth(), secondary = true)
    }
}

@Composable
fun AiResultScreen(onBack: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text("АНАЛИЗ КНИГИ", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        SectionTitle("Атомные привычки")
        Text("Джеймс Клир", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 12.sp)
        Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
            Column(Modifier.padding(18.dp)) {
                Text("✦  Краткая аннотация", fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(12.dp))
                Text("Книга показывает, как небольшие ежедневные изменения формируют устойчивые привычки. Сделайте полезное действие очевидным и простым.", fontSize = 14.sp, lineHeight = 20.sp)
            }
        }
        SectionTitle("Метрики книги")
        RowMetric("ЖАНР", "Нон-фикшн")
        RowMetric("ТЕМП", "Практичный")
        RowMetric("ЭМОЦИЯ", "Вдохновляющий")
        RowMetric("СЛОЖНОСТЬ", "Доступная")
        FolioButton("Вернуться к книге", onBack, Modifier.fillMaxWidth())
    }
}

@Composable
fun AiErrorScreen(onRetry: () -> Unit, onBack: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text("АНАЛИЗ КНИГИ", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        SectionTitle("Атомные привычки")
        Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
            Column(Modifier.padding(18.dp)) {
                Text("!  Не удалось сформировать описание", fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(12.dp))
                Text("Не удалось получить результат. Проверьте подключение к интернету и попробуйте ещё раз", fontSize = 13.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(Modifier.height(18.dp))
                FolioButton("Повторить", onRetry, Modifier.fillMaxWidth())
            }
        }
        Text("Книгу можно читать, даже если AI временно недоступен.", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 11.sp)
        FolioButton("Вернуться к книге", onBack, Modifier.fillMaxWidth(), secondary = true)
    }
}

@Composable
private fun RowMetric(label: String, value: String) {
    Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)) {
        Column(Modifier.fillMaxWidth().padding(12.dp)) {
            Text(label, fontSize = 9.sp, color = MaterialTheme.colorScheme.onSurfaceVariant, fontWeight = FontWeight.Bold)
            Text(value, fontSize = 13.sp, fontWeight = FontWeight.Medium)
        }
    }
}


@Preview(name = "AI Loading - Light", showBackground = true, showSystemUi = true)
@Composable
private fun AiLoadingLightPreview() {
    FolioTheme(darkMode = false) {
        AiLoadingScreen(onResult = {}, onError = {})
    }
}
@Preview(name = "AI Result - Light", showBackground = true, showSystemUi = true)
@Composable
private fun AiResultLightPreview() {
    FolioTheme(darkMode = false) {
        AiResultScreen(onBack = {})
    }
}
@Preview(name = "AI Error - Light", showBackground = true, showSystemUi = true)
@Composable
private fun AiErrorLightPreview() {
    FolioTheme(darkMode = false) {
        AiErrorScreen(onRetry = {}, onBack = {})
    }
}