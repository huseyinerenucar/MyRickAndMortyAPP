package com.example.rickandmorty.util.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.rickandmorty.util.network.Repository
import com.rickandmortyapi.Location

class ViewModel : ViewModel() {

    private val repository = Repository()
    private val pageListConfig: PagedList.Config = PagedList.Config.Builder()
        .setPageSize(20)
        .setPrefetchDistance(40)
        .build()

    private val locationDataSourceFactory = LocationsDataSourceFactory(viewModelScope, repository)
    val locationsPagedListLiveData: LiveData<PagedList<Location>> = LivePagedListBuilder(locationDataSourceFactory, pageListConfig).build()

    private val characterDataSourceFactory = CharactersDataSourceFactory(viewModelScope, repository)
    val charactersPagedListLiveData: LiveData<PagedList<com.rickandmortyapi.Character>> = LivePagedListBuilder(characterDataSourceFactory, pageListConfig).build()

    var lCharactersPagedListLiveData: LiveData<PagedList<com.rickandmortyapi.Character>> = LivePagedListBuilder(LCharactersDataSourceFactory(viewModelScope, repository), pageListConfig).build()

    fun renew(){
        lCharactersPagedListLiveData = LivePagedListBuilder(LCharactersDataSourceFactory(viewModelScope, repository), pageListConfig).build()
    }
}