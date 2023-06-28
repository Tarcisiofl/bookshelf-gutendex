package br.com.tarcisiofl.bookshelf.presentation.listing

import br.com.tarcisiofl.bookshelf.domain.model.Book

sealed class BookListEvent {
    data class OnBookSelected(val book: Book) : BookListEvent()
}