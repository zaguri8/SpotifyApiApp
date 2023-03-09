package com.example.hackeru.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hackeru.databinding.SearchResultItemBinding
import com.example.hackeru.models.*
import com.squareup.picasso.Picasso

class SearchResultsAdapter(
    val response: List<Data<Any>>,
    val itemClickCallback: (Data<Any>) -> Unit,
) : RecyclerView.Adapter<SearchResultsAdapter.SearchResultsViewHolder>() {


    class SearchResultsViewHolder(
        val binding: SearchResultItemBinding,
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultsViewHolder {
        val binding = SearchResultItemBinding.inflate(LayoutInflater.from(parent.context))
        return SearchResultsViewHolder(binding)
    }

    override fun getItemCount(): Int {

        return response.size
    }

    override fun onBindViewHolder(holder: SearchResultsViewHolder, position: Int) {

        val item = response[position]


        val nameTextView = holder.binding.searchItemNameTv
        val descTextView = holder.binding.searchItemDescTv
        val itemImageView = holder.binding.searchItemIv
        val ratingBar = holder.binding.searchItemRatingBar

        holder.itemView.setOnClickListener {
            itemClickCallback.invoke(item)
        }
        when (item.data) {
            is ArtistData -> {
                nameTextView.text = item.data.profile.name
                if (item.data.visuals.avatarImage.sources.isNotEmpty()) {
                    Picasso.get()
                        .load(item.data.visuals.avatarImage.sources[0].url)
                        .into(itemImageView)
                }
                descTextView.text = "artist"
            }
            is AlbumData -> {
                nameTextView.text = item.data.name
                if (item.data.coverArt.sources.isNotEmpty()) {
                    Picasso.get()
                        .load(item.data.coverArt.sources[0].url)
                        .into(itemImageView)
                }
                descTextView.text = "album"
            }
            is PlaylistData -> {
                nameTextView.text = item.data.name
                if (item.data.images.items.isNotEmpty()) {
                    if (item.data.images.items[0].sources.isNotEmpty()) {
                        Picasso.get()
                            .load(item.data.images.items[0].sources[0].url)
                            .into(itemImageView)
                    }
                }
                descTextView.text = "playlist"
            }
            is PodcastData -> {
                nameTextView.text = item.data.name
                if (item.data.coverArt.sources.isNotEmpty()) {
                    Picasso.get()
                        .load(item.data.coverArt.sources[0].url)
                        .into(itemImageView)
                }
                descTextView.text = "podcast"
            }
            is TrackData -> {
                nameTextView.text = item.data.name
                if (item.data.albumOfTrack.coverArt.sources.isNotEmpty()) {
                    Picasso.get()
                        .load(item.data.albumOfTrack.coverArt.sources[0].url)
                        .into(itemImageView)
                }
                descTextView.text = "track"
            }
            is GenreData -> {
                nameTextView.text = item.data.name
                if (item.data.image.sources.isNotEmpty()) {
                    Picasso.get()
                        .load(item.data.image.sources[0].url)
                        .into(itemImageView)
                }
                descTextView.text = "genre"
            }
        }

    }


}