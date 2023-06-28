package br.com.tarcisiofl.bookshelf.data.mapper

import br.com.tarcisiofl.bookshelf.data.local.entities.BookEntity
import br.com.tarcisiofl.bookshelf.data.remote.response.BookResponse
import br.com.tarcisiofl.bookshelf.domain.model.Book

fun BookResponse.toBookEntity(): BookEntity {
    return BookEntity(
        id = id,
        title = title,
        authors = authors.map { it.toPersonEntity() },
        translators = translators.map { it.toPersonEntity() },
        subjects = subjects,
        bookshelves = bookshelves,
        languages = languages,
        copyright = copyright,
        mediaType = mediaType,
        formats = formats,
        downloadCount = downloadCount
    )
}

fun BookEntity.toBook(): Book {
    return Book(
        id = id,
        title = title,
        authors = authors.map { it.toPerson() },
        translators = translators.map { it.toPerson() },
        subjects = subjects,
        bookshelves = bookshelves,
        languages = languages,
        copyright = copyright,
        mediaType = mediaType,
        formats = formats,
        downloadCount = downloadCount
    )
}