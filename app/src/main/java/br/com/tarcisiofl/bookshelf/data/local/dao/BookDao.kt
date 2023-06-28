package br.com.tarcisiofl.bookshelf.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import br.com.tarcisiofl.bookshelf.data.local.entities.BookEntity

@Dao
interface BookDao {
    @Upsert
    suspend fun upsertAll(books: List<BookEntity>)

    @Query("SELECT * FROM books ORDER BY download_count DESC")
    fun pagingSource(): PagingSource<Int, BookEntity>

    @Query("SELECT * FROM books WHERE id = :id")
    suspend fun getBookById(id: Int): BookEntity?

    @Query("DELETE FROM books")
    suspend fun clearAll()
}