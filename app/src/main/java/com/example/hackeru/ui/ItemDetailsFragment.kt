package com.example.hackeru.ui

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackeru.SpotifyDialog
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

    private fun openSpotify(spotifyUri: String) {
        val pm: PackageManager = requireContext().packageManager
        try {
            val info = pm.getPackageInfo("com.spotify.music", 0)
            if (info != null) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(spotifyUri)
                intent.putExtra(
                    Intent.EXTRA_REFERRER,
                    "android-app://" + requireContext().packageName
                ) // Optional: Sets the referrer to your app package name
                startActivity(intent)
            }
        } catch (e: PackageManager.NameNotFoundException) {
            val alert = SpotifyDialog()
            alert.show(requireActivity().supportFragmentManager, "SpotifyDialog")
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchViewModel.selectedItem?.let {
            if (it.data is PodcastData) {
                binding.detailsPodcastPublisher.visibility = View.VISIBLE
                binding.detailsPodcastPublisher.text = "Publisher: ${it.data.publisher.name}"
            }
            handleItemUI(it)
        }
        binding.detailsSpotifyButton.setOnClickListener {
            val item = searchViewModel.selectedItem
            if (item != null) {
                when (item.data) {
                    is ArtistData -> {
                        openSpotify(item.data.uri)
                    }
                    is AlbumData -> {
                        openSpotify(item.data.uri)
                    }
                    is PlaylistData -> {
                        openSpotify(item.data.uri)
                    }
                    is PodcastData -> {
                        openSpotify(item.data.uri)
                    }
                }
            }
        }
        binding.detailsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        searchViewModel.searchArtistAlbumsLiveData.observe(viewLifecycleOwner) {
            it?.let {
                val adapter =
                    ArtistAlbumsAdapter(it.data.artist.discography.albums
                        .items.map { album -> album.releases.items[0] })
                binding.detailsRecyclerView.adapter = adapter
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        searchViewModel.clearSelectedItem()
        searchViewModel.searchArtistAlbumsLiveData.value = null
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