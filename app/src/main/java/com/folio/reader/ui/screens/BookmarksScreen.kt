package com.folio.reader.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.folio.reader.ui.components.FolioButton
import com.folio.reader.ui.components.NoteCard
import com.folio.reader.ui.components.SectionTitle
import androidx.compose.ui.tooling.preview.Preview
import com.folio.reader.ui.theme.FolioTheme

@Composable
fun BookmarksScreen(onOpenReader: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text("ТВОИ СОХРАНЕНИЯ", color = MaterialTheme.colorScheme.primary, fontSize = 10.sp, fontWeight = FontWeight.Bold)
        SectionTitle("Закладки и заметки")
        Text("Важные мысли — под рукой", color = MaterialTheme.colorScheme.onSurfaceVariant, fontSize = 12.sp)
        NoteCard(
            title = "Цитата · Атомные привычки",
            subtitle = "Глава 08 · сегодня",
            quote = "«Окружение формирует наше поведение сильнее, чем кажется»",
            onClick = onOpenReader
        )
        NoteCard(
            title = "Закладка · Глубокая работа",
            subtitle = "Глава 02 · вчера",
            quote = "«Способность сосредоточиться — ценный навык в шумном мире»",
            onClick = onOpenReader
        )
        NoteCard(
            title = "Цитата · Думай медленно",
            subtitle = "Глава 04 · недавно",
            quote = "«Мы замечаем не всё, что происходит вокруг нас»",
            onClick = onOpenReader
        )
        Spacer(Modifier.height(4.dp))
        FolioButton("Открыть чтение", onOpenReader, Modifier.fillMaxWidth())

    }
}


@Preview(name = "Bookmarks - Light", showBackground = true, showSystemUi = true)
@Composable
private fun BookmarksLightPreview() {
    FolioTheme(darkMode = false) {
        BookmarksScreen(onOpenReader = {})
    }
}