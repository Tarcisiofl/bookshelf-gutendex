package br.com.tarcisiofl.bookshelf.data.remote.response

import com.squareup.moshi.Json

data class PersonResponse(
    val name: String,
    @field:Json(name = "birth_year") val birthYear: Long?,
    @field:Json(name = "death_year") val deathYear: Long?
)
