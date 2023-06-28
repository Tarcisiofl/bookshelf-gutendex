package br.com.tarcisiofl.bookshelf.data.remote.response

import br.com.tarcisiofl.bookshelf.utils.Format
import com.squareup.moshi.Json

data class BookResponse(
    val id: Int,
    val title: String,
    val authors: List<PersonResponse>,
    val translators: List<PersonResponse>,
    val subjects: List<String>,
    val bookshelves: List<String>,
    val languages: List<String>,
    val copyright: Boolean,
    @field:Json(name = "media_type") val mediaType: String,
    val formats: Format,
    @field:Json(name = "download_count") val downloadCount: Long
)
