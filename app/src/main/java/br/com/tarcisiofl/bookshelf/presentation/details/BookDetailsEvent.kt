package br.com.tarcisiofl.bookshelf.presentation.details

sealed class BookDetailsEvent {
    data class LoadBook(val bookId: Int) : BookDetailsEvent()
    object BackPressed : BookDetailsEvent()
}