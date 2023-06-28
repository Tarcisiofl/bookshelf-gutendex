package br.com.tarcisiofl.bookshelf.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import br.com.tarcisiofl.bookshelf.data.local.BookshelfDatabase
import br.com.tarcisiofl.bookshelf.data.remote.BookApi
import br.com.tarcisiofl.bookshelf.data.remote.BookRemoteMediator

class BooksRepository(
    private val bookshelfDatabase: BookshelfDatabase,
    private val bookApi: BookApi
) {
    @OptIn(ExperimentalPagingApi::class)
    fun getBooks() = Pager(
        config = PagingConfig(pageSize = 20),
        remoteMediator = BookRemoteMediator(
            bookshelfDatabase = bookshelfDatabase,
            bookApi = bookApi
        ),
        pagingSourceFactory = { bookshelfDatabase.bookDao.pagingSource() }
    ).flow

    suspend fun getBookById(bookId: Int) = bookshelfDatabase.bookDao.getBookById(bookId)
}