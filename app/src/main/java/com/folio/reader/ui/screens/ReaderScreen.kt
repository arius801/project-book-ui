package com.folio.reader.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.folio.reader.ui.Book
import com.folio.reader.ui.components.FolioButton
import com.folio.reader.ui.theme.FolioColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.tooling.preview.Preview
import com.folio.reader.ui.sampleBooks
import com.folio.reader.ui.theme.FolioTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReaderScreen(
    book: Book,
    darkMode: Boolean,
    onThemeChange: (Boolean) -> Unit,
    onBack: () -> Unit,
    onAi: () -> Unit
) {
    var settings by remember { mutableStateOf(false) }
    var notes by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf(false) }
    var spacing by remember { mutableIntStateOf(1) }
    val c = MaterialTheme.colorScheme
    val line = listOf(22, 27, 32)[spacing].sp

    Column(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "‹",
                    Modifier.clickable(onClick = onBack).padding(end = 12.dp),
                    fontSize = 30.sp,
                    color = c.primary
                )

                Column(Modifier.weight(1f)) {
                    Text(book.title, fontWeight = FontWeight.Bold)
                    Text(book.author, fontSize = 11.sp, color = c.onSurfaceVariant)
                }

                Text("♧", color = c.primary, fontSize = 21.sp)
            }

            HorizontalDivider(
                Modifier.padding(vertical = 16.dp),
                color = c.outline
            )

            Text(
                "ГЛАВА 08",
                color = c.primary,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                "Сделайте это\nочевидным",
                Modifier.padding(vertical = 12.dp),
                fontSize = 29.sp,
                lineHeight = 34.sp,
                fontFamily = FontFamily.Serif
            )

            listOf(
                "Самый простой способ изменить привычку — сделать сигнал заметным. Окружение влияет на поведение сильнее, чем нам кажется.",
                "Если хотите читать чаще, положите книгу на видное место. Если хотите пить больше воды — оставьте стакан на рабочем столе."
            ).forEach {
                Text(
                    it,
                    Modifier
                        .fillMaxWidth()
                        .clickable { selected = !selected }
                        .padding(4.dp),
                    fontSize = 16.sp,
                    lineHeight = line,
                    color = c.onSurface
                )
                Spacer(Modifier.height(12.dp))
            }

            if (selected) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    listOf("Выделить", "Цитата", "Заметка", "AI").forEach { action ->
                        TextButton(
                            onClick = {
                                if (action == "Заметка" || action == "Цитата") {
                                    notes = true
                                } else if (action == "AI") {
                                    onAi()
                                }
                                selected = false
                            }
                        ) {
                            Text(action, fontSize = 14.sp)
                        }
                    }
                }
            }

            Text(
                "«Сделайте правильный выбор самым простым»",
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        if (darkMode) FolioColors.DarkReaderQuote
                        else FolioColors.ReaderQuote
                    )
                    .padding(14.dp),
                color = c.primary,
                fontSize = 16.sp,
                fontFamily = FontFamily.Serif
            )
        }

        Column(
            Modifier
                .fillMaxWidth()
                .background(c.surfaceVariant)
                .padding(20.dp)
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("ГЛАВА 8 ИЗ 20", fontSize = 10.sp, color = c.onSurfaceVariant)
                Text("48%", fontSize = 10.sp, color = c.primary)
            }

            LinearProgressIndicator(
                progress = { .48f },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            FolioButton(
                "✦  AI — объяснить фрагмент",
                onAi,
                Modifier.fillMaxWidth()
            )

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                    horizontalArrangement = Arrangement.SpaceAround
            ) {
                ReaderTool("Aa", "Настройки текста") { settings = true }
                ReaderTool("↕", "Интервал") { spacing = (spacing + 1) % 3 }
                ReaderTool("✎", "Заметка") { notes = true }
            }
        }
    }

    if (settings) SettingsSheet(darkMode, onThemeChange) {
        settings = false
    }

    if (notes) NotesSheet(book.title) {
        notes = false
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsSheet(
    dark: Boolean,
    onTheme: (Boolean) -> Unit,
    close: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = close,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        SettingsSheetContent(dark, onTheme, close)
    }
}

@Composable
private fun SettingsSheetContent(
    dark: Boolean,
    onTheme: (Boolean) -> Unit,
    close: () -> Unit
) {
    val c = MaterialTheme.colorScheme

    Column(Modifier.fillMaxWidth().padding(20.dp)) {
        Text("Настройки чтения", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Настройте удобный вид текста", fontSize = 11.sp, color = c.onSurfaceVariant)
        Spacer(Modifier.height(16.dp))

        Text("ГАРНИТУРА", fontSize = 9.sp, color = c.onSurfaceVariant)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Option("Manrope", true, Modifier.weight(1f))
            Option("Roboto Serif", false, Modifier.weight(1f))
        }

        Spacer(Modifier.height(12.dp))
        Text("Размер шрифта", fontWeight = FontWeight.SemiBold)
        Option("−          18 sp          +", false, Modifier.fillMaxWidth())

        Text("Межстрочный интервал", fontWeight = FontWeight.SemiBold)
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            Option("Узкий", false, Modifier.weight(1f))
            Option("Обычный", true, Modifier.weight(1f))
            Option("Свободный", false, Modifier.weight(1f))
        }

        Text("Яркость текста", fontWeight = FontWeight.SemiBold)
        Slider(.7f, {}, enabled = false)

        Text("Тема оформления", fontWeight = FontWeight.SemiBold)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Option("🌞 День", !dark, Modifier.weight(1f)) { onTheme(false) }
            Option("🌜 Ночь", dark, Modifier.weight(1f)) { onTheme(true) }
        }

        Spacer(Modifier.height(14.dp))
        FolioButton("Готово", close, Modifier.fillMaxWidth())
    }
}


// نافذة الملاحظات الحقيقية داخل التطبيق
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NotesSheet(title: String, close: () -> Unit) {
    ModalBottomSheet(
        onDismissRequest = close,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        NotesSheetContent(title = title, close = close)
    }
}


@Composable
private fun NotesSheetContent(title: String, close: () -> Unit) {
    val c = MaterialTheme.colorScheme
    var draft by remember { mutableStateOf("") }

    Column(
        Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Text("Сохранённые заметки", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("Ваши важные мысли и цитаты", fontSize = 11.sp, color = c.onSurfaceVariant)
        Spacer(Modifier.height(14.dp))

        Text("Добавить заметку", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = draft,
            onValueChange = { draft = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Напишите свою мысль...") },
            minLines = 3,
            maxLines = 4,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences
            )
        )

        Spacer(Modifier.height(14.dp))
        NoteCard("ЦИТАТА · ${title.uppercase()}", "Окружение формирует наше поведение")
        Spacer(Modifier.height(8.dp))
        NoteCard("ЗАМЕТКА · ГЛУБОКАЯ РАБОТА", "Сосредоточение требует защиты от отвлечений")
        Spacer(Modifier.height(14.dp))

        FolioButton("Готово", close, Modifier.fillMaxWidth())
    }
}

@Composable
private fun Option(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    click: (() -> Unit)? = null
) {
    val c = MaterialTheme.colorScheme

    Box(
        modifier
            .clip(RoundedCornerShape(10.dp))
            .background(if (selected) c.primary else c.surfaceVariant)
            .then(if (click != null) Modifier.clickable(onClick = click) else Modifier)
            .padding(9.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text,
            color = if (selected) c.onPrimary else c.onSurfaceVariant,
            fontSize = 15.sp,fontWeight = FontWeight.Bold

        )
    }
}

@Composable
private fun NoteCard(title: String, text: String) {
    val c = MaterialTheme.colorScheme

    Column(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(c.surfaceVariant)
            .padding(14.dp)
    ) {
        Text(title, fontSize = 9.sp, color = c.primary, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Text(text, fontSize = 14.sp, fontFamily = FontFamily.Serif)
    }
}

@Composable
private fun ReaderTool(icon: String, label: String, click: () -> Unit) {
    Column(
        Modifier
            .clickable(onClick = click)
            .padding(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            icon,
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            label,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(name = "Reader - Light", showBackground = true, showSystemUi = true)
@Composable
private fun ReaderScreenLightPreview() {
    FolioTheme(darkMode = false) {
        ReaderScreen(
            book = sampleBooks.first(),
            darkMode = false,
            onThemeChange = {},
            onBack = {},
            onAi = {}
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Notes input - Light",
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun NotesSheetLightPreview() {
    FolioTheme(darkMode = false) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.BottomCenter
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.78f),
                shape = RoundedCornerShape(
                    topStart = 24.dp,
                    topEnd = 24.dp
                ),
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 8.dp
            ) {
                Column {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        BottomSheetDefaults.DragHandle()
                    }

                    NotesSheetContent(
                        title = sampleBooks.first().title,
                        close = {}
                    )
                }
            }
        }
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Text Settings - Light",
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun SettingsSheetLightPreview() {
    FolioTheme(darkMode = false) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.BottomCenter
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.78f),
                shape = RoundedCornerShape(
                    topStart = 24.dp,
                    topEnd = 24.dp
                ),
                color = MaterialTheme.colorScheme.surface,
                shadowElevation = 8.dp
            ) {
                Column {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        BottomSheetDefaults.DragHandle()
                    }

                    SettingsSheetContent(
                        dark = false,
                        onTheme = {},
                        close = {}
                    )
                }
            }
        }
    }
}
