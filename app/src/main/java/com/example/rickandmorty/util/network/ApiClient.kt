package com.example.rickandmorty.util.network

import com.example.rickandmorty.struct.Characters
import com.example.rickandmorty.struct.Locations
import retrofit2.Response

class ApiClient(private val rickAndMortyService: RickAndMortyService) {

    suspend fun getLocationsPage(pageIndex: Int): SimpleResponse<Locations> {
        return safeApiCall { rickAndMortyService.getLocationsAtPage(pageIndex) }
    }

    suspend fun getCharactersPage(pageIndex: Int): SimpleResponse<Characters> {
        return safeApiCall { rickAndMortyService.getCharactersAtPage(pageIndex) }
    }

    suspend fun getMultipleCharacters(chars: List<String>): SimpleResponse<List<com.rickandmortyapi.Character>> {
        return safeApiCall { rickAndMortyService.getMultipleCharacters(chars) }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            SimpleResponse.failure(e)
        }
    }
}