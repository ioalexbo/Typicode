package com.softvision.alexlepadatu.typicode.domain.useCase.albumsList

import com.softvision.alexlepadatu.typicode.domain.model.Album
import com.softvision.alexlepadatu.typicode.domain.repository.AlbumsRepository
import com.softvision.alexlepadatu.typicode.domain.common.ResultState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AlbumsUseCaseImpl @Inject constructor(private val repository: AlbumsRepository) : AlbumsUseCase {

    override fun getAlbums(): Flow<ResultState<List<Album>>> = repository.getAlbums()
}
