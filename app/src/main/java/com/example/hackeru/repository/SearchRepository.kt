package com.example.hackeru.repository

import com.example.hackeru.models.SearchCategory
import com.example.hackeru.models.SearchResponse
import com.example.hackeru.retrofit.SearchService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchRepository(
    private val service: SearchService,
) {

    suspend fun search(name: String, category: String): SearchResponse {
        return withContext(Dispatchers.IO) {
            val tracks = service.search(name, category)
            return@withContext withContext(Dispatchers.Main) {
                tracks
            }
        }
    }

}