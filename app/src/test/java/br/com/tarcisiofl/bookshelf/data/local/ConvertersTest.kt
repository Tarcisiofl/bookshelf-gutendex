package br.com.tarcisiofl.bookshelf.data.local

import br.com.tarcisiofl.bookshelf.data.local.entities.PersonEntity
import com.squareup.moshi.Moshi
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ConvertersTest {

    private lateinit var converters: Converters

    @Before
    fun setup() {
        val moshi = Moshi.Builder().build()
        converters = Converters(moshi)
    }

    @Test
    fun jsonToPersonEntityList_withValidJson_returnsPersonEntityList() {
        val json = "[{\"name\":\"John\",\"birthYear\": 1990, \"deathYear\": 2050},{\"name\":\"Jane\",\"birthYear\": 1992, \"deathYear\": 2048}]"
        val expectedList = listOf(PersonEntity("John", 1990, 2050), PersonEntity("Jane", 1992, 2048))

        val result = converters.jsonToPersonEntityList(json)

        assertEquals(expectedList, result)
    }

    @Test
    fun jsonToPersonEntityList_withInvalidJson_returnsEmptyList() {
        val json = "{\"name\":\"John\",\"birthYear\": 1990, \"deathYear\": 2050},{\"name\":\"Jane\",\"birthYear\": 1992, \"deathYear\": 2048}]"

        val result = converters.jsonToPersonEntityList(json)

        assertEquals(emptyList<PersonEntity>(), result)
    }
}