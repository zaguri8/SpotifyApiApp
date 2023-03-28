package com.example.hackeru.retrofit

import com.example.hackeru.models.ApiArtistAlbumsResponse
import com.example.hackeru.models.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface SearchService {
    @Headers(
        "X-RapidAPI-Key: 9a9d56c2f4msh53ee4d745194c32p1dc7f4jsn4e1f976bf1b2",
        "X-RapidAPI-Host: spotify23.p.rapidapi.com"
    )
    @GET("search/")
    suspend fun search(
        @Query("q") query: String,
        @Query("type") type: String = "multi",
        @Query("limit") limit: String = "10",
        @Query("offset") offset: String = "0",
        @Query("numberOfTopResults") topResults: Int = 5,
    ): SearchResponse



    @Headers(
        "X-RapidAPI-Key: 99a7fc0608mshb59fdd65c82e2e9p187226jsn7e143d48635c",
        "X-RapidAPI-Host: spotify23.p.rapidapi.com"
    )
    @GET("artist_albums/")
    suspend fun artistAlbums(
        @Query("id") id: String,
        @Query("limit") limit: String = "100",
        @Query("offset") offset: String = "0",
    ): ApiArtistAlbumsResponse

}
