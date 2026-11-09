package com.folio.reader.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.folio.reader.ui.Book
import androidx.compose.ui.tooling.preview.Preview
import com.folio.reader.ui.sampleBooks
import com.folio.reader.ui.theme.FolioTheme

@Composable
fun FolioButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    secondary: Boolean = false,
    height: Dp = 48.dp,
    fontSize: TextUnit = 14.sp
) {
    androidx.compose.material3.Button(
        onClick = onClick,
        modifier = modifier.height(height),
        shape = RoundedCornerShape(14.dp),
        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
            containerColor = if (secondary) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.primary,
            contentColor = if (secondary) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(text, fontWeight = FontWeight.SemiBold, fontSize = fontSize)
    }
}

@Composable
fun SectionTitle(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        modifier = modifier,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onBackground
    )
}

@Composable
fun BookCover(book: Book, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color(book.coverColor))
            .padding(12.dp)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(22.dp)
                .clip(RoundedCornerShape(50))
                .background(Color(0xFFD7EF72))
        )
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .width(4.dp)
                .height(88.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color(0xFFD7EF72))
        )
        Column(modifier = Modifier.align(Alignment.BottomStart)) {
            Text(
                text = book.title.uppercase(),
                color = Color.White,
                fontSize = 12.sp,
                lineHeight = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(5.dp))
            Text(
                text = book.author.uppercase(),
                color = Color.White.copy(alpha = 0.85f),
                fontSize = 7.sp
            )
        }
    }
}

@Composable
fun BookGridCard(book: Book, onClick: () -> Unit, modifier: Modifier = Modifier) {
    androidx.compose.material3.Card(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(Modifier.padding(10.dp)) {
            BookCover(book, Modifier.fillMaxWidth().height(132.dp))
            Spacer(Modifier.height(10.dp))
            Text(book.title, fontSize = 13.sp, fontWeight = FontWeight.Bold, maxLines = 1)
            Text(book.author, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant, maxLines = 1)
            Spacer(Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(7.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(horizontal = 9.dp, vertical = 4.dp)
            ) {
                Text(book.genre, fontSize = 10.sp, color = MaterialTheme.colorScheme.onPrimaryContainer)
            }
        }
    }
}

@Composable
fun BottomNavigation(
    selected: String,
    onLibrary: () -> Unit,
    onBookmarks: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .border(0.5.dp, MaterialTheme.colorScheme.outline)
            .padding(horizontal = 18.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomItem("⌂", "Книги", selected == "library", onLibrary)
        BottomItem("▣", "Закладки", selected == "bookmarks", onBookmarks)
    }
}

@Composable
private fun BottomItem(icon: String, label: String, selected: Boolean, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onClick)
            .background(if (selected) MaterialTheme.colorScheme.primaryContainer else Color.Transparent)
            .padding(horizontal = 18.dp, vertical = 5.dp)
            .then(Modifier),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = icon,
            color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 10.sp
        )
    }
}

@Composable
fun NoteCard(title: String, subtitle: String, quote: String, onClick: () -> Unit = {}) {
    androidx.compose.material3.Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = androidx.compose.material3.CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(title.uppercase(), fontSize = 9.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            Spacer(Modifier.height(8.dp))
            Text(subtitle, fontSize = 10.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(Modifier.height(10.dp))
            Text(quote, fontSize = 15.sp, lineHeight = 21.sp, color = MaterialTheme.colorScheme.onSurface, fontFamily = androidx.compose.ui.text.font.FontFamily.Serif)
        }
    }
}

@Preview(name = "Components - Light", showBackground = true)
@Composable
private fun FolioComponentsLightPreview() {
    FolioTheme(darkMode = false) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            FolioButton("Продолжить", onClick = {}, modifier = Modifier.fillMaxWidth())
            BookCover(sampleBooks.first(), Modifier.size(width = 110.dp, height = 150.dp))
            BookGridCard(sampleBooks.first(), onClick = {}, modifier = Modifier.width(180.dp))
            NoteCard(
                title = "Цитата · Атомные привычки",
                subtitle = "Глава 08 · сегодня",
                quote = "Окружение формирует наше поведение"
            )
            BottomNavigation(selected = "library", onLibrary = {}, onBookmarks = {})
        }
    }
}

