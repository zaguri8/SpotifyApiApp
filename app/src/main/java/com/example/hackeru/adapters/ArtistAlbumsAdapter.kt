package com.example.hackeru.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hackeru.databinding.SearchResultAlbumItemBinding
import com.example.hackeru.databinding.SearchResultItemBinding
import com.example.hackeru.models.Album
import com.squareup.picasso.Picasso

class ArtistAlbumsAdapter(
    private val list: List<Album>,
) : RecyclerView.Adapter<ArtistAlbumsAdapter.ArtistAlbumsViewHolder>() {


    class ArtistAlbumsViewHolder(
        private val binding: SearchResultAlbumItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Album) {
            binding.searchItemAlbumNameTv.text = item.name
            binding.searchItemAlbumDescTv.text = "Track count: ${item.tracks.totalCount}"
            val date = item.date.isoString.split("T")[0].split("-").reversed().joinToString(".")
            binding.searchItemAlbumReleaseDate.text = "Release date: $date"

            Picasso.get().load(item.coverArt.sources[0].url).into(binding.searchItemAlbumIv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistAlbumsViewHolder {
        val binding = SearchResultAlbumItemBinding.inflate(LayoutInflater.from(parent.context))
        return ArtistAlbumsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ArtistAlbumsViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

}