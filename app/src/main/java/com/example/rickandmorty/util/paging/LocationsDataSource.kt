package com.example.rickandmorty.util.paging

import androidx.paging.PageKeyedDataSource
import androidx.paging.PagingSource
import com.example.rickandmorty.util.network.Repository
import com.rickandmortyapi.Location
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LocationsDataSource(private val coroutineScope: CoroutineScope, private val repository: Repository) : PageKeyedDataSource<Int, Location>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Location>) {
        coroutineScope.launch {
            val page = repository.getLocationsPage(1)

            if (page == null) {
                callback.onResult(emptyList(), null, null)
                return@launch
            }

            callback.onResult(page.results, null, getPageIndexFromNext(page.info.next))
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Location>) {
        coroutineScope.launch {
            val page = repository.getLocationsPage(params.key)

            if (page == null) {
                callback.onResult(emptyList(), null)
                return@launch
            }

            callback.onResult(page.results, getPageIndexFromNext(page.info.next))
        }
    }

    private fun getPageIndexFromNext(next: String?): Int? {
        return next?.split("?page=")?.get(1)?.toInt()
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Location>) {
        TODO("Not yet implemented")
    }
}