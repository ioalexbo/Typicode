package com.softvision.alexlepadatu.typicode.presentation.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.softvision.alexlepadatu.typicode.databinding.CellAlbumBinding
import com.softvision.alexlepadatu.typicode.domain.model.Album

class AlbumAdapter : RecyclerView.Adapter<AlbumViewHolder>() {
    private val data: MutableList<Album> = mutableListOf()

    fun setData(data: List<Album>) {
        val diffCallback = AlbumsDiffCallback(this.data, data)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.data.clear()
        this.data.addAll(data)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding = CellAlbumBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return AlbumViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bindData(data[position])
    }
}
