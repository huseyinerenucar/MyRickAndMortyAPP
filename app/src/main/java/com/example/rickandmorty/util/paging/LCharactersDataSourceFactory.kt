package com.example.rickandmorty.util.paging

import androidx.paging.DataSource
import com.example.rickandmorty.util.network.Repository
import kotlinx.coroutines.CoroutineScope

class LCharactersDataSourceFactory(private val coroutineScope: CoroutineScope, private val repository: Repository): DataSource.Factory<Int, com.rickandmortyapi.Character>() {

    override fun create(): DataSource<Int, com.rickandmortyapi.Character> {
        return LCharactersDataSource(coroutineScope, repository)
    }
}