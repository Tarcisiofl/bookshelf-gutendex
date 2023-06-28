package br.com.tarcisiofl.bookshelf.presentation.listing

sealed class BookListViewEffect {
    data class ShowBookDetails(val bookId: Int) : BookListViewEffect()
}