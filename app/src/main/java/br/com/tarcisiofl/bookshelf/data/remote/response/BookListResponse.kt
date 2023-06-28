package br.com.tarcisiofl.bookshelf.data.remote.response

data class BookListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<BookResponse>
)
