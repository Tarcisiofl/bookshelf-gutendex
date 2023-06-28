package br.com.tarcisiofl.bookshelf.presentation.listing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import br.com.tarcisiofl.bookshelf.data.mapper.toBook
import br.com.tarcisiofl.bookshelf.data.repository.BooksRepository
import br.com.tarcisiofl.bookshelf.domain.model.Book
import kotlinx.coroutines.flow.*

class BookListingViewModel(
    private val repository: BooksRepository
) : ViewModel() {

    private val _viewEffects = MutableStateFlow<BookListViewEffect?>(null)
    val viewEffects: StateFlow<BookListViewEffect?> = _viewEffects.asStateFlow()

    fun getBooks(): Flow<PagingData<Book>> =
        repository.getBooks().map { pagingData -> pagingData.map { it.toBook() } }
            .cachedIn(viewModelScope)

    fun dispatchEvent(event: BookListEvent) {
        when (event) {
            is BookListEvent.OnBookSelected -> {
                val book = event.book
                val viewEffect = BookListViewEffect.ShowBookDetails(book.id)
                _viewEffects.value = viewEffect
            }
        }
    }

    fun clearViewEffects() {
        _viewEffects.value = null
    }
}