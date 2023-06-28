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

typealias Format = Map<String, String>