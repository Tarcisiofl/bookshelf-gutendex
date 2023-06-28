package br.com.tarcisiofl.bookshelf.presentation.details.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Download
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.tarcisiofl.bookshelf.R
import br.com.tarcisiofl.bookshelf.domain.model.Book
import br.com.tarcisiofl.bookshelf.domain.model.Person
import br.com.tarcisiofl.bookshelf.presentation.details.*
import br.com.tarcisiofl.bookshelf.ui.theme.BookshelfTheme
import br.com.tarcisiofl.bookshelf.utils.formatWithAmpersand
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun BookDetailsScreen(
    navController: NavController,
    bookId: Int,
    viewModel: BookDetailsViewModel
) {
    LaunchedEffect(bookId) {
        viewModel.dispatchEvent(BookDetailsEvent.LoadBook(bookId))
    }

    viewModel.viewEffects.collectAsState().value?.let { effect ->
        handleViewEffect(navController = navController, effect = effect)
        viewModel.clearViewEffects()
    }

    val viewStateValue =
        viewModel.models.collectAsState(initial = BookDetailsModel()).value ?: BookDetailsModel()
    BookDetailsViewStates(viewState = viewStateValue) { bookDetailsEvent ->
        viewModel.dispatchEvent(bookDetailsEvent)
    }
}

private fun handleViewEffect(
    navController: NavController,
    effect: BookDetailsViewEffect
) {
    when (effect) {
        BookDetailsViewEffect.CloseScreen -> navController.popBackStack()
    }
}

@Composable
fun BookDetailsViewStates(
    viewState: BookDetailsModel,
    actioner: (BookDetailsEvent) -> Unit
) {
    when (viewState.viewState) {
        ViewState.LOADING -> {
            LoadingState()
        }
        ViewState.LOADED -> {
            LoadedState(viewState = viewState, actioner = actioner)
        }
    }
}

@Composable
fun LoadedState(viewState: BookDetailsModel, actioner: (BookDetailsEvent) -> Unit) {
    val book = viewState.book
    if (book != null) {
        BookDetails(
            book = book,
            actioner = actioner
        )
    } else {
        Text(text = "Book not found")
    }
}

@Composable
fun LoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun BookDetails(book: Book, actioner: (BookDetailsEvent) -> Unit) {
    Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    Icons.Filled.ArrowBack, "back",
                    modifier = Modifier
                        .size(48.dp)
                        .clickable {
                            actioner(BookDetailsEvent.BackPressed)
                        }
                        .padding(start = 12.dp, top = 12.dp, bottom = 12.dp)
                )
                Text(
                    stringResource(id = R.string.bookshelf_description_title),
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 12.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold
                )
            }
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(book.formats["image/jpeg"])
                    .crossfade(true)
                    .build(),
                contentDescription = book.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
                    .padding(8.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = CornerSize(32.dp),
                            topEnd = CornerSize(32.dp),
                            bottomEnd = CornerSize(32.dp),
                            bottomStart = CornerSize(32.dp)
                        )
                    )
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 4.dp, end = 8.dp)
                        .weight(1f)
                ) {
                    Text(
                        book.title,
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.primary,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.wrapContentWidth()
                    )
                    Text(
                        stringResource(
                            id = R.string.author_prefix,
                            book.authors.formatWithAmpersand()
                        ),
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.secondary,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
                Column(
                    modifier = Modifier.padding(end = 16.dp, top = 8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .background(MaterialTheme.colors.primary)
                            .padding(8.dp)
                            .align(Alignment.End)
                    ) {
                        Row {
                            Icon(
                                Icons.Default.Download,
                                contentDescription = stringResource(id = R.string.download_count_label),
                                modifier = Modifier.padding(end = 8.dp),
                                tint = MaterialTheme.colors.onSurface
                            )
                            Text(
                                book.downloadCount.toString(),
                                style = MaterialTheme.typography.body2,
                                color = MaterialTheme.colors.onSurface
                            )
                        }
                    }
                }
            }
            BookCardInformation(book = book)
            Spacer(modifier = Modifier.height(8.dp))
            MoreInfoSection(book = book)
        }
    }
}

@Preview
@Composable
fun PreviewBookDetails() {
    BookshelfTheme {
        BookDetails(
            book = Book(
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
            ), actioner = { }
        )
    }
}

@Preview
@Composable
fun PreviewBookDetailsDarkTheme() {
    BookshelfTheme(darkTheme = true) {
        BookDetails(
            book = Book(
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
                bookshelves = listOf("Best Books Ever Listings", "Historical Fiction"),
                languages = listOf("en", "pt-br"),
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
            ), actioner = { }
        )
    }
}

