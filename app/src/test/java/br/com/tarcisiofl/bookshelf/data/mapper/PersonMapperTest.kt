package br.com.tarcisiofl.bookshelf.data.mapper

import br.com.tarcisiofl.bookshelf.data.local.entities.PersonEntity
import br.com.tarcisiofl.bookshelf.data.remote.response.PersonResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class PersonMapperTest {

    @Test
    fun testPersonResponseToPersonEntity() {
        // Given
        val personResponse = PersonResponse(
            name = "John Doe",
            birthYear = 1990,
            deathYear = 2050
        )

        // When
        val personEntity = personResponse.toPersonEntity()

        // Then
        assertEquals(personResponse.name, personEntity.name)
        assertEquals(personResponse.birthYear, personEntity.birthYear)
        assertEquals(personResponse.deathYear, personEntity.deathYear)
    }

    @Test
    fun testPersonEntityToPerson() {
        // Given
        val personEntity = PersonEntity(
            name = "John Doe",
            birthYear = 1990,
            deathYear = 2050
        )

        // When
        val person = personEntity.toPerson()

        // Then
        assertEquals(personEntity.name, person.name)
        assertEquals(personEntity.birthYear, person.birthYear)
        assertEquals(personEntity.deathYear, person.deathYear)
    }
}