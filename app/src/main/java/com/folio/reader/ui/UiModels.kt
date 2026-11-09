package com.folio.reader.ui

data class Book(
    val title: String,
    val author: String,
    val coverColor: Long,
    val genre: String,
    val pages: Int,
    val progress: Int
)

val sampleBooks = listOf(
    Book(
        title = "Атомные привычки",
        author = "Джеймс Клир",
        coverColor = 0xFF1D5F55,
        genre = "Нон-фикшн",
        pages = 320,
        progress = 68
    ),
    Book(
        title = "Глубокая работа",
        author = "Кэл Ньюпорт",
        coverColor = 0xFF203455,
        genre = "Фокус",
        pages = 296,
        progress = 24
    ),
    Book(
        title = "Думай медленно",
        author = "Даниэль Канеман",
        coverColor = 0xFFC9684F,
        genre = "Психология",
        pages = 512,
        progress = 0
    )
)

enum class AppPage {
    Library,
    Details,
    Reader,
    Bookmarks,
    AiLoading,
    AiResult,
    AiError
}
