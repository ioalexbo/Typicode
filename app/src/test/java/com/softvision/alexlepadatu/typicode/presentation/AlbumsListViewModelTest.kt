package com.softvision.alexlepadatu.typicode.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.alexlepadatu.coroutinestest.data.utils.CoroutinesRule
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.whenever
import com.softvision.alexlepadatu.typicode.domain.model.Album
import com.softvision.alexlepadatu.typicode.domain.useCase.albumsList.AlbumsUseCase
import com.softvision.alexlepadatu.typicode.presentation.fragment.AlbumsListViewModel
import com.softvision.alexlepadatu.typicode.utils.listAlbums
import com.softvision.alexlepadatu.typicode.domain.common.ResultState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class AlbumsListViewModelTest {
    @Rule
    @JvmField
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = CoroutinesRule()

    private val mockUsecase: AlbumsUseCase = mock()

    private lateinit var viewModel: AlbumsListViewModel

    @Test
    fun `test loading state emit loading view visibility`() {
        whenever(mockUsecase.getAlbums()).thenReturn(flowOf(ResultState.Loading()))

        viewModel = AlbumsListViewModel(mockUsecase)

        val observer = mock<Observer<Boolean>>()
        viewModel.loadingViewVisibility().observeForever(observer)

        verify(observer, times(1)).onChanged(true)
        verifyNoMoreInteractions(observer)
    }

    @Test
    fun `test loading state emit error view visibility`() {
        whenever(mockUsecase.getAlbums()).thenReturn(flowOf(ResultState.Loading()))

        viewModel = AlbumsListViewModel(mockUsecase)

        val observer = mock<Observer<Boolean>>()
        viewModel.errorViewVisibility().observeForever(observer)

        verify(observer, times(1)).onChanged(false)
    }

    @Test
    fun `test loading state emit no data view visibility`() {
        whenever(mockUsecase.getAlbums()).thenReturn(flowOf(ResultState.Loading()))

        viewModel = AlbumsListViewModel(mockUsecase)

        val observer = mock<Observer<Boolean>>()
        viewModel.noDataVisibility().observeForever(observer)

        verify(observer, times(1)).onChanged(false)
    }

    @Test
    fun `test error state emit loading view visibility`() {
        whenever(mockUsecase.getAlbums()).thenReturn(flowOf(ResultState.Error(Exception("Error"))))

        viewModel = AlbumsListViewModel(mockUsecase)

        val observer = mock<Observer<Boolean>>()
        viewModel.loadingViewVisibility().observeForever(observer)

        verify(observer, times(1)).onChanged(false)
        verifyNoMoreInteractions(observer)
    }

    @Test
    fun `test error state emit error view visibility`() {
        whenever(mockUsecase.getAlbums()).thenReturn(flowOf(ResultState.Error(Exception("Error"))))

        viewModel = AlbumsListViewModel(mockUsecase)

        val observer = mock<Observer<Boolean>>()
        viewModel.errorViewVisibility().observeForever(observer)

        verify(observer, times(1)).onChanged(true)
    }

    @Test
    fun `test error state emit no data view visibility`() {
        whenever(mockUsecase.getAlbums()).thenReturn(flowOf(ResultState.Error(Exception("Error"))))

        viewModel = AlbumsListViewModel(mockUsecase)

        val observer = mock<Observer<Boolean>>()
        viewModel.noDataVisibility().observeForever(observer)

        verify(observer, times(1)).onChanged(false)
    }

    @Test
    fun `test success state emit loading view visibility`() {
        val data = listAlbums()
        whenever(mockUsecase.getAlbums()).thenReturn(flowOf(ResultState.Success(data)))

        viewModel = AlbumsListViewModel(mockUsecase)

        val observer = mock<Observer<Boolean>>()
        viewModel.loadingViewVisibility().observeForever(observer)

        verify(observer, times(1)).onChanged(false)
        verifyNoMoreInteractions(observer)
    }

    @Test
    fun `test success state emit error view visibility`() {
        val data = listAlbums()
        whenever(mockUsecase.getAlbums()).thenReturn(flowOf(ResultState.Success(data)))

        viewModel = AlbumsListViewModel(mockUsecase)

        val observer = mock<Observer<Boolean>>()
        viewModel.errorViewVisibility().observeForever(observer)

        verify(observer, times(1)).onChanged(false)
    }

    @Test
    fun `test success state emit no data view visibility when data available`() {
        val data = listAlbums()
        whenever(mockUsecase.getAlbums()).thenReturn(flowOf(ResultState.Success(data)))

        viewModel = AlbumsListViewModel(mockUsecase)

        val observer = mock<Observer<Boolean>>()
        viewModel.noDataVisibility().observeForever(observer)

        verify(observer, times(1)).onChanged(false)
    }

    @Test
    fun `test success state emit no data view visibility`() {
        val data = listOf<Album>()
        whenever(mockUsecase.getAlbums()).thenReturn(flowOf(ResultState.Success(data)))

        viewModel = AlbumsListViewModel(mockUsecase)

        val observer = mock<Observer<Boolean>>()
        viewModel.noDataVisibility().observeForever(observer)

        verify(observer, times(1)).onChanged(true)
    }

    @Test
    fun `test success state emit albums data`() {
        val data = listAlbums()
        whenever(mockUsecase.getAlbums()).thenReturn(flowOf(ResultState.Success(data)))

        viewModel = AlbumsListViewModel(mockUsecase)

        val observer = mock<Observer<List<Album>>>()
        viewModel.listAlbums().observeForever(observer)

        verify(observer, times(1)).onChanged(data)
    }
}
