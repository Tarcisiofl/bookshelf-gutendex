package br.com.tarcisiofl.bookshelf.data.remote

import androidx.paging.*
import br.com.tarcisiofl.bookshelf.data.local.BookshelfDatabase
import br.com.tarcisiofl.bookshelf.data.local.dao.RemoteKeysDao
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.TimeUnit

@OptIn(ExperimentalPagingApi::class)
@RunWith(MockitoJUnitRunner::class)
class BookRemoteMediatorTest {

    @Mock
    private lateinit var bookshelfDatabase: BookshelfDatabase

    @Mock
    private lateinit var remoteKeysDaoMock: RemoteKeysDao

    @Mock
    private lateinit var bookApi: BookApi

    private lateinit var bookRemoteMediator: BookRemoteMediator

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        bookRemoteMediator = BookRemoteMediator(bookshelfDatabase, bookApi)
    }

    @Test
    fun testInitialize_ReturnsSkipInitialRefreshIfCacheNotExpired() = runBlocking {
        val cacheTimeout = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)
        val creationTime = System.currentTimeMillis() - cacheTimeout / 2
        `when`(bookshelfDatabase.remoteKeysDao).thenReturn(remoteKeysDaoMock)
        `when`(remoteKeysDaoMock.getCreationTime()).thenReturn(creationTime)

        val initializeAction = bookRemoteMediator.initialize()
        assertEquals(RemoteMediator.InitializeAction.SKIP_INITIAL_REFRESH, initializeAction)
    }

    @Test
    fun testInitialize_ReturnsLaunchInitialRefreshIfCacheExpired() = runBlocking {
        val cacheTimeout = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)
        val creationTime = System.currentTimeMillis() - cacheTimeout * 2
        `when`(bookshelfDatabase.remoteKeysDao).thenReturn(remoteKeysDaoMock)
        `when`(remoteKeysDaoMock.getCreationTime()).thenReturn(creationTime)

        val initializeAction = bookRemoteMediator.initialize()
        assertEquals(RemoteMediator.InitializeAction.LAUNCH_INITIAL_REFRESH, initializeAction)
    }
}