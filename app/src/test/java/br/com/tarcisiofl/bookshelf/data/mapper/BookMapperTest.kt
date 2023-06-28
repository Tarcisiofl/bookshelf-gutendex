package br.com.tarcisiofl.bookshelf.data.mapper

import br.com.tarcisiofl.bookshelf.data.local.entities.BookEntity
import br.com.tarcisiofl.bookshelf.data.local.entities.PersonEntity
import br.com.tarcisiofl.bookshelf.data.remote.response.BookResponse
import br.com.tarcisiofl.bookshelf.data.remote.response.PersonResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class BookMapperTest {

    @Test
    fun testBookResponseToBookEntity() {
        // Given
        val bookResponse = BookResponse(
            id = 1,
            title = "Book Title",
            authors = listOf(PersonResponse(name = "Author 1", birthYear = 1990, deathYear = 2050)),
            translators = listOf(
                PersonResponse(
                    name = "Translator 1",
                    birthYear = 1990,
                    deathYear = 2050
                )
            ),
            subjects = listOf("Subject 1"),
            bookshelves = listOf("Bookshelf 1"),
            languages = listOf("Language 1"),
            copyright = false,
            mediaType = "Paperback",
            formats = mapOf(
                "applicationXMobipocketEbook" to "https://www.gutenberg.org/ebooks/1513.kf8.images",
                "applicationEpubZip" to "https://www.gutenberg.org/ebooks/1513.epub3.images"
            ),
            downloadCount = 100
        )

        // When
        val bookEntity = bookResponse.toBookEntity()

        // Then
        assertEquals(bookResponse.id, bookEntity.id)
        assertEquals(bookResponse.title, bookEntity.title)
        assertEquals(1, bookEntity.authors.size)
        assertEquals("Author 1", bookEntity.authors[0].name)
        assertEquals(1, bookEntity.translators.size)
        assertEquals("Translator 1", bookEntity.translators[0].name)
        assertEquals(1, bookEntity.subjects.size)
        assertEquals("Subject 1", bookEntity.subjects[0])
        assertEquals(1, bookEntity.bookshelves.size)
        assertEquals("Bookshelf 1", bookEntity.bookshelves[0])
        assertEquals(1, bookEntity.languages.size)
        assertEquals("Language 1", bookEntity.languages[0])
        assertEquals(bookResponse.copyright, bookEntity.copyright)
        assertEquals(bookResponse.mediaType, bookEntity.mediaType)
        assertEquals(2, bookEntity.formats.size)
        assertEquals(
            "https://www.gutenberg.org/ebooks/1513.kf8.images",
            bookEntity.formats["applicationXMobipocketEbook"]
        )
        assertEquals(
            "https://www.gutenberg.org/ebooks/1513.epub3.images",
            bookEntity.formats["applicationEpubZip"]
        )
        assertEquals(bookResponse.downloadCount, bookEntity.downloadCount)
    }

    @Test
    fun testBookEntityToBook() {
        // Given
        val bookEntity = BookEntity(
            id = 1,
            title = "Book Title",
            authors = listOf(PersonEntity(name = "Author 1", birthYear = 1990, deathYear = 2050)),
            translators = listOf(
                PersonEntity(
                    name = "Translator 1",
                    birthYear = 1990,
                    deathYear = 2050
                )
            ),
            subjects = listOf("Subject 1"),
            bookshelves = listOf("Bookshelf 1"),
            languages = listOf("Language 1"),
            copyright = false,
            mediaType = "Paperback",
            formats = mapOf(
                "applicationXMobipocketEbook" to "https://www.gutenberg.org/ebooks/1513.kf8.images",
                "applicationEpubZip" to "https://www.gutenberg.org/ebooks/1513.epub3.images"
            ),
            downloadCount = 100
        )

        // When
        val book = bookEntity.toBook()

        // Then
        assertEquals(bookEntity.id, book.id)
        assertEquals(bookEntity.title, book.title)
        assertEquals(1, book.authors.size)
        assertEquals("Author 1", book.authors[0].name)
        assertEquals(1, book.translators.size)
        assertEquals("Translator 1", book.translators[0].name)
        assertEquals(1, book.subjects.size)
        assertEquals("Subject 1", book.subjects[0])
        assertEquals(1, book.bookshelves.size)
        assertEquals("Bookshelf 1", book.bookshelves[0])
        assertEquals(1, book.languages.size)
        assertEquals("Language 1", book.languages[0])
        assertEquals(bookEntity.copyright, book.copyright)
        assertEquals(bookEntity.mediaType, book.mediaType)
        assertEquals(2, book.formats.size)
        assertEquals(
            "https://www.gutenberg.org/ebooks/1513.kf8.images",
            book.formats["applicationXMobipocketEbook"]
        )
        assertEquals(
            "https://www.gutenberg.org/ebooks/1513.epub3.images",
            book.formats["applicationEpubZip"]
        )
        assertEquals(bookEntity.downloadCount, book.downloadCount)
    }
}