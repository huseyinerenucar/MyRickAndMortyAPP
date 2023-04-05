package com.example.rickandmorty.util.paging

import androidx.paging.DataSource
import com.example.rickandmorty.util.network.Repository
import com.rickandmortyapi.Location
import kotlinx.coroutines.CoroutineScope

class LocationsDataSourceFactory(private val coroutineScope: CoroutineScope, private val repository: Repository): DataSource.Factory<Int, Location>() {

    override fun create(): DataSource<Int, Location> {
        return LocationsDataSource(coroutineScope, repository)
    }
}