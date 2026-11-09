package com.folio.reader.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.folio.reader.ui.components.BottomNavigation
import com.folio.reader.ui.screens.AiErrorScreen
import com.folio.reader.ui.screens.AiLoadingScreen
import com.folio.reader.ui.screens.AiResultScreen
import com.folio.reader.ui.screens.BookDetailsScreen
import com.folio.reader.ui.screens.BookmarksScreen
import com.folio.reader.ui.screens.LibraryScreen
import com.folio.reader.ui.screens.ReaderScreen
import com.folio.reader.ui.theme.FolioTheme

@Composable
fun FolioApp() {
    // Одна переменная управляет текущей страницей.
    var page by remember { mutableStateOf(AppPage.Library) }
    var selectedBook by remember { mutableStateOf(sampleBooks.first()) }
    var darkMode by remember { mutableStateOf(false) }

    FolioTheme(darkMode = darkMode) {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
            bottomBar = {
                if (page == AppPage.Library || page == AppPage.Bookmarks) {
                    BottomNavigation(
                        selected = if (page == AppPage.Library) "library" else "bookmarks",
                        onLibrary = { page = AppPage.Library },
                        onBookmarks = { page = AppPage.Bookmarks }
                    )
                }
            }
        ) { padding ->
            androidx.compose.foundation.layout.Box(
                modifier = Modifier.fillMaxSize().padding(padding)
            ) {
                when (page) {
                    AppPage.Library -> LibraryScreen(
                        onBookClick = { book ->
                            selectedBook = book
                            page = AppPage.Details
                        },
                        onContinue = { page = AppPage.Reader }
                    )

                    AppPage.Details -> BookDetailsScreen(
                        book = selectedBook,
                        onBack = { page = AppPage.Library },
                        onRead = { page = AppPage.Reader },
                        onAi = { page = AppPage.AiLoading }
                    )

                    AppPage.Reader -> ReaderScreen(
                        book = selectedBook,
                        darkMode = darkMode,
                        onThemeChange = { darkMode = it },
                        onBack = { page = AppPage.Details },
                        onAi = { page = AppPage.AiLoading }
                    )

                    AppPage.Bookmarks -> BookmarksScreen(
                        onOpenReader = { page = AppPage.Reader }
                    )

                    AppPage.AiLoading -> AiLoadingScreen(
                        onResult = { page = AppPage.AiResult },
                        onError = { page = AppPage.AiError }
                    )

                    AppPage.AiResult -> AiResultScreen(
                        onBack = { page = AppPage.Reader }
                    )

                    AppPage.AiError -> AiErrorScreen(
                        onRetry = { page = AppPage.AiLoading },
                        onBack = { page = AppPage.Reader }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Folio Reader")
@Composable
private fun FolioAppPreview() {
    FolioApp()
}