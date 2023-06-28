package br.com.tarcisiofl.bookshelf.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.tarcisiofl.bookshelf.utils.Format

@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val authors: List<PersonEntity>,
    val translators: List<PersonEntity>,
    val subjects: List<String>,
    val bookshelves: List<String>,
    val languages: List<String>,
    val copyright: Boolean,
    @ColumnInfo(name = "media_type") val mediaType: String,
    val formats: Format,
    @ColumnInfo(name = "download_count") val downloadCount: Long
)
