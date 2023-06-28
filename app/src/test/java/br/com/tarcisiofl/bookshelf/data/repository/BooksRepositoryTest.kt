package br.com.tarcisiofl.bookshelf.data.repository

import br.com.tarcisiofl.bookshelf.data.local.BookshelfDatabase
import br.com.tarcisiofl.bookshelf.data.local.dao.BookDao
import br.com.tarcisiofl.bookshelf.data.local.entities.BookEntity
import br.com.tarcisiofl.bookshelf.data.local.entities.PersonEntity
import br.com.tarcisiofl.bookshelf.data.remote.BookApi
import br.com.tarcisiofl.bookshelf.data.remote.BookRemoteMediator
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class BooksRepositoryTest {
    @Mock
    private lateinit var bookshelfDatabase: BookshelfDatabase

    @Mock
    private lateinit var bookDaoMock: BookDao

    @Mock
    private lateinit var bookApi: BookApi

    private lateinit var bookRemoteMediator: BookRemoteMediator

    private lateinit var repository: BooksRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = BooksRepository(bookshelfDatabase, bookApi)
        bookRemoteMediator = BookRemoteMediator(bookshelfDatabase, bookApi)
    }

    @Test
    fun `getBookById should return the book with the given ID`() = runBlocking {
        val bookId = 1513
        val expectedBook = getFirstBook()

        `when`(bookshelfDatabase.bookDao).thenReturn(bookDaoMock)
        `when`(bookDaoMock.getBookById(bookId)).thenReturn(expectedBook)

        val actualBook = repository.getBookById(bookId)

        assertEquals(expectedBook, actualBook)
    }

    private fun getFirstBook(): BookEntity =
        BookEntity(
            id = 1513,
            title = "Romeo and Juliet",
            authors = listOf(
                PersonEntity(
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
            formats = mapOf(
                "applicationXMobipocketEbook" to "https://www.gutenberg.org/ebooks/1513.kf8.images",
                "applicationEpubZip" to "https://www.gutenberg.org/ebooks/1513.epub3.images"
            ),
            downloadCount = 103473
        )
}