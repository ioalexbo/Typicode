package com.softvision.alexlepadatu.typicode.data.api

import com.softvision.alexlepadatu.typicode.data.model.dto.AlbumDto
import retrofit2.http.GET

interface AlbumsApi {

    @GET("/albums")
    suspend fun getAlbums(): List<AlbumDto>
}
