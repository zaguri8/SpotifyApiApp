package com.example.hackeru.models


/*
 Search models --------------------------------------------
 */
class SharingInfo(
    val shareUrl: String,
)

class CovertArtSource(
    val url: String,
    val width: Int,
    val height: Int,
)

class CoverArt(
    val sources: List<CovertArtSource>,
)

class AlbumOfTrack(
    val id: String,
    val uri: String,
    val name: String,
    val coverArt: CoverArt,
    val sharingInfo: SharingInfo,
)

class ContentRating(
    val label: String,
)

class Duration(
    val totalMilliseconds: Long,
)

class Playability(
    val playable: Boolean,
)

class Images(
    val items: List<CoverArt>,
)

class ArtistProfile(
    val name: String,
)

class ArtistOfTrack(
    val uri: String,
    val profile: ArtistProfile,
)

class Artists(
    val items: List<ArtistOfTrack>,
)

class TrackData(
    val id: String,
    val name: String,
    val albumOfTrack: AlbumOfTrack,
    val contentRating: ContentRating,
    val duration: Duration,
    val playability: Playability,
)

class GenreData(
    val uri: String,
    val name: String,
    val image: CoverArt,
)

class ArtistsOfAlbum(
    val items: List<ArtistOfTrack>,
)


class Visuals(
    val avatarImage: CoverArt,
)

class Owner(
    val name: String,
)

class ResponseDate(val year: Long)


class AlbumData(
    val uri: String,
    val name: String,
    val artists: ArtistsOfAlbum,
    val coverArt: CoverArt,
    val date: ResponseDate,
)

class ArtistData(
    val uri: String,
    val profile: ArtistProfile,
    val visuals: Visuals,
) {
    override fun toString(): String {
        return "Name: ${profile.name}, Image: ${if (visuals.avatarImage.sources.isNotEmpty()) visuals.avatarImage.sources[0].url else ""}"
    }
}

class Publisher(val name: String)

class PlaylistData(
    val uri: String,
    val name: String,
    val description: String,
    val images: Images,
    val owner: Owner,
)

class PodcastData(
    val uri: String,
    val name: String,
    val coverArt: CoverArt,
    val type: String,
    val publisher: Publisher,
    val mediaType: String,
)


open class ApiResults<T>(val items: List<Data<T>>, val totalCount: Long) {
    override fun toString(): String {
        return "totalCount: $totalCount , Data: $items"
    }
}

class PodcastResults(
    items: List<Data<PodcastData>>,
    totalCount: Long,
) : ApiResults<PodcastData>(items, totalCount)

class PlaylistResults(
    items: List<Data<PlaylistData>>,
    totalCount: Long,
) : ApiResults<PlaylistData>(items, totalCount)


class GenreResults(
    items: List<Data<GenreData>>,
    totalCount: Long,
) : ApiResults<GenreData>(items, totalCount)

class TrackResults(
    items: List<Data<TrackData>>,
    totalCount: Long,
) : ApiResults<TrackData>(items, totalCount)

class AlbumResults(
    items: List<Data<AlbumData>>,
    totalCount: Long,
) : ApiResults<AlbumData>(items, totalCount)

class ArtistResults(
    items: List<Data<ArtistData>>,
    totalCount: Long,
) : ApiResults<ArtistData>(items, totalCount)

open class Data<T>(val data: T)

// --------------------------------------------

class ApiDate(
    val year: Long,
    val isoString: String,
)
class AlbumTrackCount(
    val totalCount: Long,
)
class ApiArtistAlbumsResponseHolder(
    val artist: ArtistAlbumsDiscography,
)
class ApiArtistAlbumsResponse(
    data: ApiArtistAlbumsResponseHolder,
) : Data<ApiArtistAlbumsResponseHolder>(data)
class Album(
    val id: String,
    val name: String,
    val coverArt: CoverArt,
    val date: ApiDate,
    val type: String,
    val tracks: AlbumTrackCount,
)
class ArtistAlbum(
    val items: List<Album>,
)
class ArtistAlbumReleases(
    val releases: ArtistAlbum,
)
class Albums(
    val items: List<ArtistAlbumReleases>,
    val totalCount: Long,
)
class ArtistAlbumsDiscography(
    val discography: Artist
)

class Artist(
    val albums: Albums,
)
