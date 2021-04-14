package com.softvision.alexlepadatu.typicode.domain.useCase.albumsList

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.softvision.alexlepadatu.typicode.domain.model.Album
import com.softvision.alexlepadatu.typicode.domain.repository.AlbumsRepository
import com.softvision.alexlepadatu.typicode.utils.listAlbums
import com.softvision.alexlepadatu.typicode.domain.common.ResultState
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class AlbumsUseCaseTest {
    private val repository: AlbumsRepository = mock()
    private lateinit var albumsUseCase: AlbumsUseCase

    @Before
    fun setup() {
        albumsUseCase = AlbumsUseCaseImpl(repository)
    }

    @Test
    fun `test receive state`() = runBlocking {
        val loadingState = ResultState.Loading<List<Album>>()

        whenever(repository.getAlbums()).thenReturn(flowOf(loadingState))

        val result = albumsUseCase.getAlbums()

        Assert.assertEquals(loadingState, result.first())
    }

    @Test
    fun `test receive multiple states`() = runBlocking {
        val loadingState = ResultState.Loading<List<Album>>()
        val successState = ResultState.Success(listAlbums())
        val flow = flowOf(loadingState, successState)

        whenever(repository.getAlbums()).thenReturn(flow)

        val result = albumsUseCase
            .getAlbums()
            .toList()
            .toTypedArray()

        Assert.assertArrayEquals(arrayOf(loadingState, successState), result)
    }
}
