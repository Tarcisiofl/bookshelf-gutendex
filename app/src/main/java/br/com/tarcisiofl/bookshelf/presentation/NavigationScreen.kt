package br.com.tarcisiofl.bookshelf.presentation

sealed class Screen {

    abstract fun getCalculatedRoute(): String

    data class BookDetailsScreen(val bookId: Int) : Screen() {
        override fun getCalculatedRoute(): String {
            return "/details/$bookId"
        }
    }
}

enum class ScreenRoute(val route: String) {
    BOOK_LISTING_SCREEN("/list"),
    BOOK_DETAILS_SCREEN("/details/{bookId}")
}