package br.com.tarcisiofl.bookshelf.presentation.details

import br.com.tarcisiofl.bookshelf.data.local.entities.BookEntity
import br.com.tarcisiofl.bookshelf.data.local.entities.PersonEntity
import br.com.tarcisiofl.bookshelf.data.repository.BooksRepository
import br.com.tarcisiofl.bookshelf.domain.model.Book
import br.com.tarcisiofl.bookshelf.domain.model.Person
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class BookDetailsViewModelTest {
    @InjectMockKs
    private lateinit var viewModel: BookDetailsViewModel

    @MockK
    private lateinit var repository: BooksRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @ExperimentalCoroutinesApi
    @After
    fun cleanup() {
        Dispatchers.resetMain()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `dispatchEvent with LoadBook event should load book`() = runBlocking {
        val bookId = 2701
        val book = getBook()
        val bookEntity = getBookEntity()

        coEvery { repository.getBookById(bookId) } returns bookEntity

        viewModel.dispatchEvent(BookDetailsEvent.LoadBook(bookId))

        coVerify(exactly = 1) { repository.getBookById(bookId) }

        assertEquals(book, viewModel.models.value?.book)
        assertEquals(ViewState.LOADED, viewModel.models.value?.viewState)
    }

    @Test
    fun `dispatchEvent with LoadBook event and null response should set model with null book`() =
        runBlocking {
            val bookId = 1
            coEvery { repository.getBookById(bookId) } returns null

            viewModel.dispatchEvent(BookDetailsEvent.LoadBook(bookId))

            coVerify(exactly = 1) { repository.getBookById(bookId) }
            assertEquals(null, viewModel.models.value?.book)
            assertEquals(ViewState.LOADED, viewModel.models.value?.viewState)
        }

    @Test
    fun `dispatchEvent with LoadBook event and exception should set model with null book`() =
        runBlocking {
            val bookId = 1
            coEvery { repository.getBookById(bookId) } throws RuntimeException()

            viewModel.dispatchEvent(BookDetailsEvent.LoadBook(bookId))

            coVerify(exactly = 1) { repository.getBookById(bookId) }
            assertEquals(null, viewModel.models.value?.book)
            assertEquals(ViewState.LOADED, viewModel.models.value?.viewState)
        }


    @Test
    fun `dispatchEvent with BackPressed event should set view effect to CloseScreen`() {
        viewModel.dispatchEvent(BookDetailsEvent.BackPressed)
        assertEquals(BookDetailsViewEffect.CloseScreen, viewModel.viewEffects.value)
    }

    @Test
    fun `clearViewEffects should set viewEffects to null`() {
        val viewEffect = BookDetailsViewEffect.CloseScreen
        viewModel.dispatchEvent(BookDetailsEvent.BackPressed)
        assertEquals(viewEffect, viewModel.viewEffects.value)

        viewModel.clearViewEffects()

        assertEquals(null, viewModel.viewEffects.value)
    }

    private fun getBookEntity(): BookEntity =
        BookEntity(
            id = 2701,
            title = "Moby Dick; Or, The Whale",
            authors = listOf(
                PersonEntity(
                    name = "Melville, Herman",
                    birthYear = 1819,
                    deathYear = 1891
                )
            ),
            translators = emptyList(),
            subjects = listOf(
                "Adventure stories",
                "Ahab, Captain (Fictitious character) -- Fiction",
                "Mentally ill -- Fiction",
                "Psychological fiction",
                "Sea stories",
                "Ship captains -- Fiction",
                "Whales -- Fiction",
                "Whaling -- Fiction",
                "Whaling ships -- Fiction"
            ),
            bookshelves = listOf("Best Books Ever Listings"),
            languages = listOf("en"),
            copyright = false,
            mediaType = "Text",
            formats = mapOf(
                "applicationXMobipocketEbook" to "https://www.gutenberg.org/ebooks/2701.kf8.images",
                "applicationEpubZip" to "https://www.gutenberg.org/ebooks/2701.epub3.images"
            ),
            downloadCount = 103473
        )

    private fun getBook(): Book =
        Book(
            id = 2701,
            title = "Moby Dick; Or, The Whale",
            authors = listOf(
                Person(
                    name = "Melville, Herman",
                    birthYear = 1819,
                    deathYear = 1891
                )
            ),
            translators = emptyList(),
            subjects = listOf(
                "Adventure stories",
                "Ahab, Captain (Fictitious character) -- Fiction",
                "Mentally ill -- Fiction",
                "Psychological fiction",
                "Sea stories",
                "Ship captains -- Fiction",
                "Whales -- Fiction",
                "Whaling -- Fiction",
                "Whaling ships -- Fiction"
            ),
            bookshelves = listOf("Best Books Ever Listings"),
            languages = listOf("en"),
            copyright = false,
            mediaType = "Text",
            formats = mapOf(
                "applicationXMobipocketEbook" to "https://www.gutenberg.org/ebooks/2701.kf8.images",
                "applicationEpubZip" to "https://www.gutenberg.org/ebooks/2701.epub3.images"
            ),
            downloadCount = 103473
        )
}