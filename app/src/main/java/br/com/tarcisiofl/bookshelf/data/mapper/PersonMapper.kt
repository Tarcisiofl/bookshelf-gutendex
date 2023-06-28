package br.com.tarcisiofl.bookshelf.data.mapper

import br.com.tarcisiofl.bookshelf.data.local.entities.PersonEntity
import br.com.tarcisiofl.bookshelf.data.remote.response.PersonResponse
import br.com.tarcisiofl.bookshelf.domain.model.Person

fun PersonResponse.toPersonEntity(): PersonEntity {
    return PersonEntity(
        name = name,
        birthYear = birthYear,
        deathYear = deathYear
    )
}

fun PersonEntity.toPerson(): Person {
    return Person(
        name = name,
        birthYear = birthYear,
        deathYear = deathYear
    )
}