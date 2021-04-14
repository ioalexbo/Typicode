package com.softvision.alexlepadatu.typicode.presentation.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softvision.alexlepadatu.typicode.domain.model.Album
import com.softvision.alexlepadatu.typicode.domain.useCase.albumsList.AlbumsUseCase
import com.softvision.alexlepadatu.typicode.domain.common.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsListViewModel @Inject constructor (private val usecase: AlbumsUseCase) : ViewModel() {
    private val listAlbums = MutableLiveData<List<Album>>()
    private val noDataVisibility = MutableLiveData<Boolean>()
    private val errorViewVisibility = MutableLiveData<Boolean>()
    private val loadingViewVisibility = MutableLiveData<Boolean>()

    init {
        fetchAlbums()
    }

    fun listAlbums(): LiveData<List<Album>> = listAlbums

    fun noDataVisibility(): LiveData<Boolean> = noDataVisibility

    fun errorViewVisibility(): LiveData<Boolean> = errorViewVisibility

    fun loadingViewVisibility(): LiveData<Boolean> = loadingViewVisibility

    private fun fetchAlbums() {
        viewModelScope.launch {
            usecase.getAlbums()
                .collect { result: ResultState<List<Album>> ->
                    when (result) {
                        is ResultState.Loading -> {
                            loadingViewVisibility.value = true
                            errorViewVisibility.value = false
                            noDataVisibility.value = false
                        }

                        is ResultState.Error -> {
                            loadingViewVisibility.value = false
                            errorViewVisibility.value = true
                            noDataVisibility.value = false
                        }

                        is ResultState.Success -> {
                            errorViewVisibility.value = false
                            loadingViewVisibility.value = false
                            noDataVisibility.value = result.data.isEmpty()
                            listAlbums.value = result.data
                        }
                    }
            }
        }
    }
}
