package br.com.tarcisiofl.bookshelf.data.remote

import br.com.tarcisiofl.bookshelf.data.remote.response.BookListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApi {
    @GET("books")
    suspend fun getBooks(
        @Query("page") page: Int?
    ): BookListResponse

    companion object {
        const val BASE_URL = "https://gutendex.com"
    }
}