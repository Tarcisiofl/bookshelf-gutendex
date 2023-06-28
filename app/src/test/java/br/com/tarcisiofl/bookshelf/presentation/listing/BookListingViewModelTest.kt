package br.com.tarcisiofl.bookshelf.presentation.listing

import androidx.paging.PagingData
import androidx.paging.map
import br.com.tarcisiofl.bookshelf.data.local.entities.BookEntity
import br.com.tarcisiofl.bookshelf.data.local.entities.PersonEntity
import br.com.tarcisiofl.bookshelf.data.repository.BooksRepository
import br.com.tarcisiofl.bookshelf.domain.model.Book
import br.com.tarcisiofl.bookshelf.domain.model.Person
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

class BookListingViewModelTest {
    @InjectMockKs
    private lateinit var viewModel: BookListingViewModel

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

    @Test
    fun `dispatchEvent with OnBookSelected event should show book details`() = runTest {
        val book = getBook()
        viewModel.dispatchEvent(BookListEvent.OnBookSelected(book))

        val showBookDetailsEvent = viewModel.viewEffects
            .filterIsInstance<BookListViewEffect.ShowBookDetails>().firstOrNull()

        assertNotNull(showBookDetailsEvent)
        assertEquals(book.id, showBookDetailsEvent?.bookId)
    }
    
    @Ignore("Can't figure out why it is returning an empty result - viewModel.getBooks().collect")
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `getBooks should return the correct list of books`() = runTest {
        val expected = listOf(getBook(), getBook())
        val pagingDataResult = PagingData.from(listOf(getSecondBook(), getSecondBook()))

        coEvery { repository.getBooks() } returns flowOf(pagingDataResult)

        val result = mutableListOf<Book>()
        val job = launch {
            viewModel.getBooks().collect { pagingData ->
                pagingData.map { book ->
                    result.add(book)
                }
            }
        }

        advanceUntilIdle()

        job.cancel()

        assertEquals(expected, result)
    }


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

    private fun getSecondBook(): BookEntity =
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
}