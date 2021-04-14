package com.softvision.alexlepadatu.typicode.domain.useCase.albumsList

import com.softvision.alexlepadatu.typicode.domain.common.ResultState
import com.softvision.alexlepadatu.typicode.domain.model.Album
import kotlinx.coroutines.flow.Flow

interface AlbumsUseCase {
    fun getAlbums(): Flow<ResultState<List<Album>>>
}
