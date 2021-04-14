package com.softvision.alexlepadatu.typicode.data.model.mappers

import com.softvision.alexlepadatu.typicode.data.model.entity.AlbumEntity
import com.softvision.alexlepadatu.typicode.domain.model.Album

fun AlbumEntity.toDomain(): Album = Album(id, userId, title)

fun List<AlbumEntity>.toDomain(): List<Album> = map { it.toDomain() }
