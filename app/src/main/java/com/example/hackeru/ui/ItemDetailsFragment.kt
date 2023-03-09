package com.example.hackeru.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackeru.adapters.ArtistAlbumsAdapter
import com.example.hackeru.databinding.FragmentItemDetailsBinding
import com.example.hackeru.models.*
import com.example.hackeru.viewmodel.SearchViewModel
import com.squareup.picasso.Picasso

class ItemDetailsFragment : Fragment() {

    private var _binding: FragmentItemDetailsBinding? = null
    private val binding: FragmentItemDetailsBinding get() = _binding!!

    private lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        searchViewModel = ViewModelProvider(requireActivity())[SearchViewModel::class.java]
        _binding = FragmentItemDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchViewModel.selectedItem.observe(viewLifecycleOwner) {
            handleItemUI(it)
        }
        binding.detailsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        searchViewModel.searchArtistAlbumsLiveData.observe(viewLifecycleOwner) {
            println(it.data.artist.discography.albums.items)
            val adapter =
                ArtistAlbumsAdapter(it.data.artist.discography.albums
                    .items.map { album -> album.releases.items[0] })
            binding.detailsRecyclerView.adapter = adapter
        }
    }

    private fun showArtistAlbums(artist: ArtistData) {
        searchViewModel.searchArtistAlbums(artist)
    }

    private fun handleItemUI(item: Data<Any>) {

        when (item.data) {
            is ArtistData -> {
                binding.detailsName.text = item.data.profile.name
                if (item.data.visuals.avatarImage.sources.isNotEmpty()) {
                    Picasso.get()
                        .load(item.data.visuals.avatarImage.sources[0].url)
                        .into(binding.detailsImage)
                }
                binding.detailsDesc.text = "artist"
                showArtistAlbums(item.data)
            }
            is AlbumData -> {
                binding.detailsName.text = item.data.name
                if (item.data.coverArt.sources.isNotEmpty()) {
                    Picasso.get()
                        .load(item.data.coverArt.sources[0].url)
                        .into(binding.detailsImage)
                }
                binding.detailsDesc.text = "album"
            }
            is PlaylistData -> {
                binding.detailsName.text = item.data.name
                if (item.data.images.items.isNotEmpty()) {
                    if (item.data.images.items[0].sources.isNotEmpty()) {
                        Picasso.get()
                            .load(item.data.images.items[0].sources[0].url)
                            .into(binding.detailsImage)
                    }
                }
                binding.detailsDesc.text = "playlist"
            }
            is PodcastData -> {
                binding.detailsName.text = item.data.name
                if (item.data.coverArt.sources.isNotEmpty()) {
                    Picasso.get()
                        .load(item.data.coverArt.sources[0].url)
                        .into(binding.detailsImage)
                }
                binding.detailsDesc.text = "podcast"
            }
            is TrackData -> {
                binding.detailsName.text = item.data.name
                if (item.data.albumOfTrack.coverArt.sources.isNotEmpty()) {
                    Picasso.get()
                        .load(item.data.albumOfTrack.coverArt.sources[0].url)
                        .into(binding.detailsImage)
                }
                binding.detailsDesc.text = "track"
            }
            is GenreData -> {
                binding.detailsName.text = item.data.name
                if (item.data.image.sources.isNotEmpty()) {
                    Picasso.get()
                        .load(item.data.image.sources[0].url)
                        .into(binding.detailsImage)
                }
                binding.detailsDesc.text = "genre"
            }
        }
    }
}