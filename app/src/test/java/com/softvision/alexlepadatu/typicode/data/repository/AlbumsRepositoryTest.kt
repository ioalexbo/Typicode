package com.softvision.alexlepadatu.typicode.data.repository

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.softvision.alexlepadatu.typicode.data.api.AlbumsApi
import com.softvision.alexlepadatu.typicode.data.db.dao.AlbumsDao
import com.softvision.alexlepadatu.typicode.domain.common.ResultState
import com.softvision.alexlepadatu.typicode.domain.model.Album
import com.softvision.alexlepadatu.typicode.domain.repository.AlbumsRepository
import com.softvision.alexlepadatu.typicode.utils.listAlbums
import com.softvision.alexlepadatu.typicode.utils.listAlbumsDto
import com.softvision.alexlepadatu.typicode.utils.listAlbumsEntity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class AlbumsRepositoryTest {
    private val api: AlbumsApi = mock()
    private val dao: AlbumsDao = mock()

    private lateinit var repository: AlbumsRepository

    @Before
    fun setup() {
        repository = AlbumsRepositoryImpl(api, dao)
    }

    @Test
    fun `test don't use network api when cached data is available`() = runBlocking {
        whenever(dao.getAlbums()).thenReturn(flowOf(listOf()))
        whenever(api.getAlbums()).thenReturn(listAlbumsDto())

        repository.getAlbums()

        Mockito.verifyNoMoreInteractions(api)
    }

    @Test
    fun `test repository values when using data from cache`() = runBlocking {
        whenever(dao.getAlbums()).thenReturn(flowOf(listAlbumsEntity()))
        whenever(api.getAlbums()).thenReturn(listAlbumsDto())

        val test = repository
            .getAlbums()
            .toList()
            .toTypedArray()

        val expectedLoading: ResultState<List<Album>> = ResultState.Loading()
        val expectedSuccess: ResultState<List<Album>> = ResultState.Success(listAlbums())
        Assert.assertArrayEquals(arrayOf(expectedLoading, expectedSuccess), test)
    }

    @Test
    fun `test use network api when no cache values`() = runBlocking {
        whenever(dao.getAlbums()).thenReturn(flowOf(listOf()))
        whenever(api.getAlbums()).thenReturn(listAlbumsDto())

        repository.getAlbums().collect {}

        Mockito.verify(api).getAlbums()
        Mockito.verifyNoMoreInteractions(api)
    }
}
