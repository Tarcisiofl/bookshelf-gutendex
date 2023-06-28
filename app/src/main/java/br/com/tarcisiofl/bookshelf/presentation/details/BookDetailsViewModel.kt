package br.com.tarcisiofl.bookshelf.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.tarcisiofl.bookshelf.data.mapper.toBook
import br.com.tarcisiofl.bookshelf.data.repository.BooksRepository
import br.com.tarcisiofl.bookshelf.domain.model.Book
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BookDetailsViewModel(
    private val repository: BooksRepository
) : ViewModel() {

    private val _viewEffects = MutableStateFlow<BookDetailsViewEffect?>(null)
    val viewEffects: StateFlow<BookDetailsViewEffect?> = _viewEffects.asStateFlow()

    private val _models = MutableStateFlow<BookDetailsModel?>(null)
    val models: StateFlow<BookDetailsModel?> = _models

/* Removed because was affecting the Unit Test
   But it is necessary to see the loading on the Screen
   Because the response is too fast
*/
//    private suspend fun delayLoadingState() {
//        delay(1500)
//    }

    private fun getBookById(bookId: Int) {
        viewModelScope.launch {
            try {
//                delayLoadingState()
                val response = repository.getBookById(bookId)
                val book = response?.toBook()

                if (book != null) {
                    _models.value = BookDetailsModel(book = book, viewState = ViewState.LOADED)
                } else {
                    _models.value = BookDetailsModel(book = null, viewState = ViewState.LOADED)
                }
            } catch (exception: Exception) {
                _models.value = BookDetailsModel(book = null, viewState = ViewState.LOADED)
            }
        }
    }

    fun dispatchEvent(event: BookDetailsEvent) {
        when (event) {
            BookDetailsEvent.BackPressed -> {
                val viewEffect = BookDetailsViewEffect.CloseScreen
                _viewEffects.value = viewEffect
            }
            is BookDetailsEvent.LoadBook -> {
                _models.value = BookDetailsModel(book = null, viewState = ViewState.LOADING)
                getBookById(event.bookId)
            }
        }
    }

    fun clearViewEffects() {
        _viewEffects.value = null
    }
}