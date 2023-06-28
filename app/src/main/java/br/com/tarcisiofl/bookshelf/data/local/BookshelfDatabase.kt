package br.com.tarcisiofl.bookshelf.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.tarcisiofl.bookshelf.data.local.dao.BookDao
import br.com.tarcisiofl.bookshelf.data.local.dao.RemoteKeysDao
import br.com.tarcisiofl.bookshelf.data.local.entities.BookEntity
import br.com.tarcisiofl.bookshelf.data.local.entities.RemoteKeysEntity

@Database(
    entities = [BookEntity::class, RemoteKeysEntity::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class BookshelfDatabase : RoomDatabase() {
    abstract val bookDao: BookDao
    abstract val remoteKeysDao: RemoteKeysDao

    companion object {
        const val DB_NAME = "bookshelf_db"
    }
}