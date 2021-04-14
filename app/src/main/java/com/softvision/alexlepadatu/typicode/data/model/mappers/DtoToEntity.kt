package com.softvision.alexlepadatu.typicode.data.model.mappers

import com.softvision.alexlepadatu.typicode.data.model.dto.AlbumDto
import com.softvision.alexlepadatu.typicode.data.model.entity.AlbumEntity

fun AlbumDto.toEntity(): AlbumEntity = AlbumEntity(id, userId, title)

fun List<AlbumDto>.toEntity(): List<AlbumEntity> = map { it.toEntity() }
