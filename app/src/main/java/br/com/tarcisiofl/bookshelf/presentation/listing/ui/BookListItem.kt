package br.com.tarcisiofl.bookshelf.presentation.listing.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.tarcisiofl.bookshelf.R
import br.com.tarcisiofl.bookshelf.domain.model.Book
import br.com.tarcisiofl.bookshelf.domain.model.Person
import br.com.tarcisiofl.bookshelf.ui.theme.BookshelfTheme
import br.com.tarcisiofl.bookshelf.utils.formatWithAmpersand
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun BookListItem(book: Book, onBookClick: (Book) -> Unit) {
    Card(
        modifier = Modifier
            .padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 8.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable {
                onBookClick(book)
            },
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
        ) {
            Card(
                modifier = Modifier
                    .padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 8.dp)
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium),
                elevation = 8.dp
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(book.formats["image/jpeg"])
                        .crossfade(true)
                        .build(),
                    contentDescription = book.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    book.title,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp),
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    stringResource(
                        id = R.string.author_prefix,
                        book.authors.formatWithAmpersand()
                    ),
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 2.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
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
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(
                        book.downloadCount.toString(),
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.onSurface
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview
@Composable
fun BookListItemPreview() {
    BookshelfTheme {
        BookListItem(
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
            ),
            onBookClick = { }
        )
    }
}