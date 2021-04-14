package com.softvision.alexlepadatu.typicode.data.repository

import com.softvision.alexlepadatu.typicode.data.api.AlbumsApi
import com.softvision.alexlepadatu.typicode.data.db.dao.AlbumsDao
import com.softvision.alexlepadatu.typicode.data.model.dto.AlbumDto
import com.softvision.alexlepadatu.typicode.data.model.entity.AlbumEntity
import com.softvision.alexlepadatu.typicode.data.model.mappers.toDomain
import com.softvision.alexlepadatu.typicode.data.model.mappers.toEntity
import com.softvision.alexlepadatu.typicode.domain.model.Album
import com.softvision.alexlepadatu.typicode.domain.repository.AlbumsRepository
import com.softvision.alexlepadatu.typicode.domain.common.ResultState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AlbumsRepositoryImpl @Inject constructor(
    private val api: AlbumsApi,
    private val dao: AlbumsDao
) : AlbumsRepository {

    @ExperimentalCoroutinesApi
    override fun getAlbums(): Flow<ResultState<List<Album>>> {
        return object : NetworkBoundResource<List<AlbumDto>, List<AlbumEntity>, List<Album>>() {
            override fun query(): Flow<List<AlbumEntity>> {
                return dao.getAlbums()
            }

            override suspend fun fetchFromRemote(): List<AlbumDto> {
                return api.getAlbums()
            }

            override suspend fun saveFetchResult(data: List<AlbumEntity>) {
                dao.insertAll(*data.toTypedArray())
            }

            override fun shouldFetch(data: List<AlbumEntity>): Boolean {
                return data.isNullOrEmpty()
            }

            override suspend fun entityToDomain(data: List<AlbumEntity>): List<Album> {
                return data.toDomain()
            }

            override suspend fun dtoToEntity(data: List<AlbumDto>): List<AlbumEntity> {
                return data.toEntity()
            }
        }
            .asFlow()
    }
}
