package br.com.tarcisiofl.bookshelf.presentation.details.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.tarcisiofl.bookshelf.R
import br.com.tarcisiofl.bookshelf.domain.model.Book
import br.com.tarcisiofl.bookshelf.domain.model.Person
import br.com.tarcisiofl.bookshelf.ui.theme.BookshelfTheme

@Composable
fun MoreInfoSection(book: Book) {
    Column {
        if (book.subjects.isNotEmpty()) {
            Text(
                text = stringResource(id = R.string.subjects_subtitle),
                style = MaterialTheme.typography.subtitle2,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            )
            BulletList(
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.body2,
                lineSpacing = 8.dp, items = book.subjects
            )
        }

        if (book.bookshelves.isNotEmpty()) {
            Text(
                text = stringResource(id = R.string.bookshelves_subtitle),
                style = MaterialTheme.typography.subtitle2,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            )
            BulletList(
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.body2,
                lineSpacing = 8.dp, items = book.bookshelves
            )
        }
    }
}

@Preview
@Composable
fun PreviewMoreInfoSection() {
    BookshelfTheme {
        MoreInfoSection(
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
                bookshelves = listOf("Best Books Ever Listings"),
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
        )
    }
}