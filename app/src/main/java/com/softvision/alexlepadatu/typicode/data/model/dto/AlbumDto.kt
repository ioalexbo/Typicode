package com.softvision.alexlepadatu.typicode.data.model.dto

import com.squareup.moshi.Json

data class AlbumDto(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "userId") val userId: Int,
    @field:Json(name = "title") val title: String
)
