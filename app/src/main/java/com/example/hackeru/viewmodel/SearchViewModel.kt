package com.example.hackeru.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackeru.models.ApiArtistAlbumsResponse
import com.example.hackeru.models.ArtistData
import com.example.hackeru.models.Data
import com.example.hackeru.models.SearchResponse
import com.example.hackeru.repository.SearchRepository
import com.example.hackeru.retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchViewModel : ViewModel() {

    private val repository: SearchRepository = SearchRepository(RetrofitClient.getArtistsService())
    val searchLiveData = MutableLiveData<SearchResponse>()

    val searchArtistAlbumsLiveData = MutableLiveData<ApiArtistAlbumsResponse?>()

    var selectedItem: Data<Any>? = null

    fun selectItem(data: Data<Any>) {
        selectedItem = data
    }

    fun clearSelectedItem() {
        selectedItem = null
    }

    fun search(name: String, category: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val response = repository.search(name, category)
                    searchLiveData.postValue(response)
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                    println(e.message)
                }
            }
        }
    }

    fun searchArtistAlbums(artist: ArtistData) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val response = repository.searchArtistAlbums(artist)
                    searchArtistAlbumsLiveData.postValue(response)
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                    println(e.message)
                }
            }
        }
    }


}