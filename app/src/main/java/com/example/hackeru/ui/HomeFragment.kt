package com.example.hackeru.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.hackeru.R
import com.example.hackeru.adapters.HomeCollectionAdapter
import com.example.hackeru.databinding.FragmentHomeBinding
import com.example.hackeru.models.Data
import com.example.hackeru.viewmodel.SearchViewModel


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    val binding get() = _binding!!

    lateinit var viewModel: SearchViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[SearchViewModel::class.java]
        return binding.root
    }

    fun getRandomLetter(): String {
        val alphabet = "abcdefghijklmnopqrstuvwxyz"
        return alphabet.random().toString()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(viewModel.searchLiveData.value == null)
            viewModel.search(getRandomLetter(), "multi")

        viewModel.searchLiveData.observe(viewLifecycleOwner) { searchResults ->

            val albums = searchResults.albums?.items
            val artists = searchResults.artists?.items
            val tracks = searchResults.tracks?.items
            val podcasts = searchResults.podcasts?.items
            val playlists = searchResults.playlists?.items
            val dividerItemDecoration = SpacesItemDecoration(20)
            binding.albumsRv.addItemDecoration(dividerItemDecoration)
            binding.artistsRv.addItemDecoration(dividerItemDecoration)
            binding.musicRv.addItemDecoration(dividerItemDecoration)
            binding.podcastRv.addItemDecoration(dividerItemDecoration)
            binding.playlistsRv.addItemDecoration(dividerItemDecoration)

            albums?.let {
                binding.albumsRv.adapter = HomeCollectionAdapter(it.toDataList()) { data ->
                    viewModel.selectItem(data)
                    findNavController()
                        .navigate(R.id.action_homeFragment_to_item_details_fragment)
                }
            }
            artists?.let {
                binding.artistsRv.adapter = HomeCollectionAdapter(it.toDataList()) { data ->
                    viewModel.selectItem(data)
                    findNavController()
                        .navigate(R.id.action_homeFragment_to_item_details_fragment)
                }
            }
            tracks?.let {
                binding.musicRv.adapter = HomeCollectionAdapter(it.toDataList()) { data ->
                    viewModel.selectItem(data)
                    findNavController()
                        .navigate(R.id.action_homeFragment_to_item_details_fragment)
                }
            }

            podcasts?.let {
                binding.podcastRv.adapter = HomeCollectionAdapter(it.toDataList()) { data ->
                    viewModel.selectItem(data)
                    findNavController()
                        .navigate(R.id.action_homeFragment_to_item_details_fragment)
                }
            }

            playlists?.let {
                binding.playlistsRv.adapter = HomeCollectionAdapter(it.toDataList()) { data ->
                    viewModel.selectItem(data)
                    findNavController()
                        .navigate(R.id.action_homeFragment_to_item_details_fragment)
                }
            }
        }
    }
}

fun <T> List<Data<T>>.toDataList(): List<Data<Any>> {
    val list = mutableListOf<Data<Any>>()
    this.forEach {
        list.add(it as Data<Any>)
    }
    return list
}