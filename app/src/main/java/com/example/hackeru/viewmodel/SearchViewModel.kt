package com.example.hackeru.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _selectedItem = MutableLiveData<Data<Any>>()
    val selectedItem: LiveData<Data<Any>> = _selectedItem

    fun selectItem(data: Data<Any>) {
        _selectedItem.postValue(data)
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


}