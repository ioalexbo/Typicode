package com.softvision.alexlepadatu.typicode.presentation.fragment.adapter

import androidx.recyclerview.widget.RecyclerView
import com.softvision.alexlepadatu.typicode.databinding.CellAlbumBinding
import com.softvision.alexlepadatu.typicode.domain.model.Album

class AlbumViewHolder(private val binding: CellAlbumBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindData(album: Album) {
        binding.lblAlbum.text = album.title
    }
}
