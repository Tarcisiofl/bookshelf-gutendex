package br.com.tarcisiofl.bookshelf.utils

import br.com.tarcisiofl.bookshelf.domain.model.Person

fun List<String>.formatWithCommas(): String {
    val stringBuilder = StringBuilder()
    for (i in indices) {
        stringBuilder.append(this[i])
        if (i < size - 1) {
            stringBuilder.append(", ")
        }
    }
    return stringBuilder.toString()
}


fun List<Person>.formatWithAmpersand(): String {
    val stringBuilder = StringBuilder()
    for (i in indices) {
        stringBuilder.append(this[i].name)
        if (i < size - 1) {
            stringBuilder.append(" & ")
        }
    }
    return stringBuilder.toString()
}

fun List<Person>.formatWithAmpersandAndDates(): String {
    val stringBuilder = StringBuilder()
    for (i in indices) {
        val person = this[i]
        val authorString = buildAuthorString(person)
        stringBuilder.append(authorString)
        if (i < size - 1) {
            stringBuilder.append(" & ")
        }
    }
    return stringBuilder.toString()
}

private fun buildAuthorString(person: Person): String {
    val stringBuilder = StringBuilder()
    stringBuilder.append(person.name)
    if (person.birthYear != null && person.deathYear != null) {
        stringBuilder.append(" (${person.birthYear} - ${person.deathYear})")
    }
    return stringBuilder.toString()
}


typealias Format = Map<String, String>