package com.softvision.alexlepadatu.typicode.utils

import com.softvision.alexlepadatu.typicode.data.model.dto.AlbumDto
import com.softvision.alexlepadatu.typicode.data.model.entity.AlbumEntity
import com.softvision.alexlepadatu.typicode.domain.model.Album

fun listAlbumsEntity() = listOf(AlbumEntity(1, 1, "quidem molestiae enim"))

fun listAlbumsDto() = listOf(AlbumDto(1, 1, "quidem molestiae enim"))

fun listAlbums() = listOf(Album(1, 1, "quidem molestiae enim"))


