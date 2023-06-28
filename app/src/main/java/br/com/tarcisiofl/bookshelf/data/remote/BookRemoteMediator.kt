package br.com.tarcisiofl.bookshelf.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import br.com.tarcisiofl.bookshelf.data.local.BookshelfDatabase
import br.com.tarcisiofl.bookshelf.data.local.entities.BookEntity
import br.com.tarcisiofl.bookshelf.data.local.entities.RemoteKeysEntity
import br.com.tarcisiofl.bookshelf.data.mapper.toBookEntity
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalPagingApi::class)
class BookRemoteMediator(
    private val bookshelfDatabase: BookshelfDatabase,
    private val bookApi: BookApi
) : RemoteMediator<Int, BookEntity>() {

    override suspend fun initialize(): InitializeAction {
        val cacheTimeout = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)
        val creationTime = bookshelfDatabase.remoteKeysDao.getCreationTime()

        return if (System.currentTimeMillis() - (creationTime ?: 0) < cacheTimeout) {
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BookEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevKey = remoteKeys?.prevKey
                    prevKey
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextKey = remoteKeys?.nextKey
                    nextKey
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
            }

            val apiResponse = bookApi.getBooks(page = loadKey)
            val books = apiResponse.results

            val endOfPaginationReached = books.isEmpty()

            bookshelfDatabase.withTransaction {
                if (loadType == LoadType.REFRESH && books.isNotEmpty()) {
                    bookshelfDatabase.remoteKeysDao.clearAll()
                    bookshelfDatabase.bookDao.clearAll()
                }

                val prevKey = if (loadKey > 1) loadKey - 1 else null
                val nextKey = if (endOfPaginationReached) null else loadKey + 1
                val remoteKeys = books.map {
                    RemoteKeysEntity(
                        bookId = it.id,
                        prevKey = prevKey,
                        currentPage = loadKey,
                        nextKey = nextKey
                    )
                }

                val bookEntities = books.map { it.toBookEntity() }
                bookshelfDatabase.remoteKeysDao.insertAll(remoteKeys)
                bookshelfDatabase.bookDao.upsertAll(bookEntities)
            }

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, BookEntity>): RemoteKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                bookshelfDatabase.remoteKeysDao.getRemoteKeyByBookID(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, BookEntity>): RemoteKeysEntity? {
        return state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { book ->
            bookshelfDatabase.remoteKeysDao.getRemoteKeyByBookID(book.id)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, BookEntity>): RemoteKeysEntity? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { book ->
            bookshelfDatabase.remoteKeysDao.getRemoteKeyByBookID(book.id)
        }
    }
}