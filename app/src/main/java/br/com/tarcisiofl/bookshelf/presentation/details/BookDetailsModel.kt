package br.com.tarcisiofl.bookshelf.presentation.details

import br.com.tarcisiofl.bookshelf.domain.model.Book

data class BookDetailsModel(
    val viewState: ViewState = ViewState.LOADING,
    val book: Book? = null
)

enum class ViewState {
    LOADING,
    LOADED
}
