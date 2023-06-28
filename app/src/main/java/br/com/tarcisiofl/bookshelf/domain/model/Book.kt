package br.com.tarcisiofl.bookshelf.domain.model

import br.com.tarcisiofl.bookshelf.utils.Format

data class Book(
    val id: Int,
    val title: String,
    val authors: List<Person>,
    val translators: List<Person>,
    val subjects: List<String>,
    val bookshelves: List<String>,
    val languages: List<String>,
    val copyright: Boolean,
    val mediaType: String,
    val formats: Format,
    val downloadCount: Long
)
