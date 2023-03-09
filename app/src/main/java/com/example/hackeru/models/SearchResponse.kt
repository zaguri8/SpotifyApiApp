package com.example.hackeru.models

class SearchResponse(
    val tracks: TrackResults?,
    val artists: ArtistResults?,
    val albums: AlbumResults?,
    val playlists: PlaylistResults?,
    val podcasts: PodcastResults?,
) {
    override fun toString(): String {
        return "Tracks: (${tracks}), Artists: (${artists}), Albums: (${albums}), Playlists: (${playlists}), Podcasts: (${podcasts})"
    }

    fun toDataList(): List<Data<Any>> {
        val list = mutableListOf<Any>()
        if (tracks != null) {
            list.addAll(tracks.items)
        }
        if (artists != null) {
            list.addAll(artists.items)
        }
        if (playlists != null) {
            list.addAll(playlists.items)
        }
        if (podcasts != null) {
            list.addAll(podcasts.items)
        }
        if (albums != null) {
            list.addAll(albums.items)
        }
        return list as List<Data<Any>>
    }
}