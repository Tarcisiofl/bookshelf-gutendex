package br.com.tarcisiofl.bookshelf.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.tarcisiofl.bookshelf.data.local.entities.RemoteKeysEntity

@Dao
interface RemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeysEntity>)

    @Query("SELECT * FROM remote_key WHERE book_id = :id")
    suspend fun getRemoteKeyByBookID(id: Int): RemoteKeysEntity?

    @Query("DELETE FROM remote_key")
    suspend fun clearAll()

    @Query("SELECT created_at FROM remote_key ORDER BY created_at DESC LIMIT 1")
    suspend fun getCreationTime(): Long?
}