package com.softvision.alexlepadatu.typicode.presentation.fragment.adapter

import androidx.recyclerview.widget.DiffUtil
import com.softvision.alexlepadatu.typicode.domain.model.Album

class AlbumsDiffCallback(private val oldList: List<Album>, private val newList: List<Album>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return oldList[oldPosition] == newList[newPosition]
    }
}
