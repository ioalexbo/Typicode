package com.softvision.alexlepadatu.typicode.domain.repository

import com.softvision.alexlepadatu.typicode.domain.model.Album
import com.softvision.alexlepadatu.typicode.domain.common.ResultState
import kotlinx.coroutines.flow.Flow

interface AlbumsRepository {
    fun getAlbums(): Flow<ResultState<List<Album>>>
}
