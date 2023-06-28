package br.com.tarcisiofl.bookshelf.domain.model

data class Person(
    val name: String,
    val birthYear: Long? = null,
    val deathYear: Long? = null
)
