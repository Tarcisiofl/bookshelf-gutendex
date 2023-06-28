package br.com.tarcisiofl.bookshelf.data.local.entities

data class PersonEntity(
    val name: String,
    val birthYear: Long?,
    val deathYear: Long?
)