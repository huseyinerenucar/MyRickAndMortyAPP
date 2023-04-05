package com.example.rickandmorty.util.paging

import androidx.paging.PageKeyedDataSource
import androidx.paging.PagingSource
import com.example.rickandmorty.util.network.Repository
import com.rickandmortyapi.Character
import com.rickandmortyapi.Location
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class LCharactersDataSource(private val coroutineScope: CoroutineScope, private val repository: Repository) : PageKeyedDataSource<Int, Character>() {

    private val characterList = mutableListOf<String>()

    companion object {
        var location : Location? = null
    }
    private var pageCount: Int = 0

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Character>) { coroutineScope.launch {

        for (character in location!!.residents) {
            characterList.add(character.id.toString())
        }

        pageCount = if(characterList.size >= 20){
            20
        } else{
            characterList.size
        }

        val page = repository.getCharactersFromLocationResponse(characterList.subList(0, pageCount))

        callback.onResult(page, null, pageCount)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {coroutineScope.launch {

        if(pageCount != 0){
            val tempCount = pageCount
            pageCount = if(characterList.size - pageCount >= 20){
                tempCount + 20
            } else{
                tempCount + characterList.size - pageCount
            }

            if (pageCount-tempCount == 0) {
                callback.onResult(emptyList(), null)
                return@launch
            }

            val page = repository.getCharactersFromLocationResponse(characterList.subList(tempCount, pageCount))

            callback.onResult(page, pageCount - tempCount)
        }
        else{
            callback.onResult(emptyList(), null)
            return@launch
        }

    }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        TODO("Not yet implemented")
    }

}