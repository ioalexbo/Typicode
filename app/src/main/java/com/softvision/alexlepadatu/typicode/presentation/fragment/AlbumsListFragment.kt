package com.softvision.alexlepadatu.typicode.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.softvision.alexlepadatu.typicode.databinding.FragmentAlbumsListBinding
import com.softvision.alexlepadatu.typicode.presentation.fragment.adapter.AlbumAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class AlbumsListFragment : Fragment() {
    private val viewModel: AlbumsListViewModel by viewModels()

    private lateinit var binding: FragmentAlbumsListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAlbumsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservables()
    }

    private fun initObservables() {
        val albumAdapter = AlbumAdapter()
        binding.rvData.apply {
            adapter = albumAdapter
        }

        viewModel.listAlbums().observe(viewLifecycleOwner, Observer {
            albumAdapter.setData(it)
        })

        viewModel.noDataVisibility().observe(viewLifecycleOwner, Observer {
            binding.viewNoData.isVisible = it
        })

        viewModel.loadingViewVisibility().observe(viewLifecycleOwner, Observer {
            binding.viewLoading.isVisible = it
        })

        viewModel.errorViewVisibility().observe(viewLifecycleOwner, Observer {
            binding.viewError.isVisible = it
        })
    }
}
