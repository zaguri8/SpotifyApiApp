package com.example.hackeru.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.hackeru.databinding.HorizontalItemBinding
import com.example.hackeru.models.*
import com.squareup.picasso.Picasso

class HomeCollectionAdapter(
    private val response: List<Data<Any>>,
    private val itemClickCallback: (Data<Any>) -> Unit,
) : RecyclerView.Adapter<HomeCollectionAdapter.HomeCollectionViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCollectionViewHolder {
        val binding = HorizontalItemBinding.inflate(LayoutInflater.from(parent.context))
        return HomeCollectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeCollectionViewHolder, position: Int) {
        val item = response[position]


        val itemNameFull = holder.binding.horizontalItemNameFull
        val itemGenre = holder.binding.horizontalItemGenre
        val itemImage = holder.binding.horizontalItemImage

        holder.itemView.setOnClickListener {
            itemClickCallback.invoke(item)
        }
        when (item.data) {
            is ArtistData -> {
                itemNameFull.text = item.data.profile.name
                if (item.data.visuals.avatarImage.sources.isNotEmpty()) {
                    Picasso.get()
                        .load(item.data.visuals.avatarImage.sources[0].url)
                        .into(itemImage)
                }
                itemGenre.text = "artist"
            }
            is AlbumData -> {
                itemNameFull.text = item.data.name
                if (item.data.coverArt.sources.isNotEmpty()) {
                    Picasso.get()
                        .load(item.data.coverArt.sources[0].url)
                        .into(itemImage)
                }
                itemGenre.text = "album"
            }
            is PlaylistData -> {
                itemNameFull.text = item.data.name
                if (item.data.images.items.isNotEmpty()) {
                    if (item.data.images.items[0].sources.isNotEmpty()) {
                        Picasso.get()
                            .load(item.data.images.items[0].sources[0].url)
                            .into(itemImage)
                    }
                }
                itemGenre.text = "playlist"
            }
            is PodcastData -> {
                itemNameFull.text = item.data.name
                if (item.data.coverArt.sources.isNotEmpty()) {
                    Picasso.get()
                        .load(item.data.coverArt.sources[0].url)
                        .into(itemImage)
                }
                itemGenre.text = "podcast"
            }
            is TrackData -> {
                itemNameFull.text = item.data.name
                if (item.data.albumOfTrack.coverArt.sources.isNotEmpty()) {
                    Picasso.get()
                        .load(item.data.albumOfTrack.coverArt.sources[0].url)
                        .into(itemImage)
                }
                itemGenre.text = "track"
            }
            is GenreData -> {
                itemNameFull.text = item.data.name
                if (item.data.image.sources.isNotEmpty()) {
                    Picasso.get()
                        .load(item.data.image.sources[0].url)
                        .into(itemImage)
                }
                itemGenre.text = "genre"
            }
        }
    }

    override fun getItemCount(): Int {
        return response.size
    }


    class HomeCollectionViewHolder(
        val binding: HorizontalItemBinding,
    ) : ViewHolder(binding.root)


}