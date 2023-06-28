package br.com.tarcisiofl.bookshelf.presentation.listing.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.tarcisiofl.bookshelf.R
import br.com.tarcisiofl.bookshelf.domain.model.Book
import br.com.tarcisiofl.bookshelf.domain.model.Person
import br.com.tarcisiofl.bookshelf.presentation.Screen
import br.com.tarcisiofl.bookshelf.presentation.listing.BookListEvent
import br.com.tarcisiofl.bookshelf.presentation.listing.BookListViewEffect
import br.com.tarcisiofl.bookshelf.presentation.listing.BookListingViewModel
import br.com.tarcisiofl.bookshelf.ui.theme.BookshelfTheme
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun BookListingScreen(navController: NavController, viewModel: BookListingViewModel) {
    val books = viewModel.getBooks().collectAsLazyPagingItems()

    DisposableEffect(Unit) {
        onDispose {
            viewModel.clearViewEffects()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.viewEffects.collect { viewEffect ->
            if (viewEffect is BookListViewEffect.ShowBookDetails) {
                navController.navigate(
                    Screen.BookDetailsScreen(viewEffect.bookId).getCalculatedRoute()
                )
            }
        }
    }

    BookListScreenContent(books = books) { book ->
        viewModel.dispatchEvent(BookListEvent.OnBookSelected(book))
    }
}

@Composable
fun BookListScreenContent(books: LazyPagingItems<Book>, onBookSelected: (Book) -> Unit) {
    Column {
        Row(
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .padding(end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.bookshelf_title),
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.primary,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(
                        start = 24.dp,
                        end = 24.dp,
                        top = 24.dp,
                        bottom = 8.dp
                    )
            )
        }
        BookGrid(modifier = Modifier.padding(start = 12.dp, end = 12.dp), books) { book ->
            onBookSelected(book)
        }
    }
}

@Preview
@Composable
fun BookListScreenContentPreview() {
    BookshelfTheme {
        val data = List(25) {
            Book(
                id = 1513,
                title = "Romeo and Juliet",
                authors = listOf(
                    Person(
                        name = "Shakespeare,  William",
                        birthYear = 1564,
                        deathYear = 1616
                    )
                ),
                translators = emptyList(),
                subjects = listOf(
                    "Conflict of generations -- Drama",
                    "Juliet (Fictitious character) -- Drama",
                    "Romeo (Fictitious character) -- Drama",
                    "Tragedies",
                    "Vendetta -- Drama",
                    "Verona (Italy) -- Drama",
                    "Youth -- Drama"
                ),
                bookshelves = emptyList(),
                languages = listOf("en"),
                copyright = false,
                mediaType = "Text",
                formats = HashMap<String, String>().apply {
                    put(
                        "applicationXMobipocketEbook",
                        "https://www.gutenberg.org/ebooks/1513.kf8.images"
                    )
                    put("applicationEpubZip", "https://www.gutenberg.org/ebooks/1513.epub3.images")
                    put("textHTML", "https://www.gutenberg.org/ebooks/1513.html.images")
                    put("applicationOctetStream", "https://www.gutenberg.org/files/1513/1513-0.zip")
                    put(
                        "image/jpeg",
                        "https://www.gutenberg.org/cache/epub/1513/pg1513.cover.medium.jpg"
                    )
                    put("textPlain", "https://www.gutenberg.org/ebooks/1513.txt.utf-8")
                    put(
                        "textPlainCharsetUsASCII",
                        "https://www.gutenberg.org/files/1513/1513-0.txt"
                    )
                    put("applicationRDFXML", "https://www.gutenberg.org/ebooks/1513.rdf")
                },
                downloadCount = 103473
            )
        }
        val flow = MutableStateFlow(PagingData.from(data))
        val lazyPagingItems = flow.collectAsLazyPagingItems()
        BookListScreenContent(books = lazyPagingItems, onBookSelected = {})
    }
}