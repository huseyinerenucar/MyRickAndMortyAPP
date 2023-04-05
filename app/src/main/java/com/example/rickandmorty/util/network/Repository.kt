package com.example.rickandmorty.util.network

import com.example.rickandmorty.struct.Characters
import com.example.rickandmorty.struct.Locations

class Repository {

    suspend fun getLocationsPage(pageIndex: Int): Locations? {
        val request = NetworkLayer.apiClient.getLocationsPage(pageIndex)
        if (request.failed || !request.isSuccessful) {
            return null

        }

        return request.body
    }

    suspend fun getCharactersPage(pageIndex: Int): Characters? {
        val request = NetworkLayer.apiClient.getCharactersPage(pageIndex)
        if (request.failed || !request.isSuccessful) {
            return null

        }

        return request.body
    }

   /* suspend fun getMultipleCharacters(chars: List<String>): List<com.rickandmortyapi.Character>? {
        val request = NetworkLayer.apiClient.getMultipleCharacters(chars)
        if (request.failed || !request.isSuccessful) {
            return null

        }

        return request.body
    }*/

    suspend fun getCharactersFromLocationResponse(charList: List<String>): List<com.rickandmortyapi.Character> {
        val request = NetworkLayer.apiClient.getMultipleCharacters(charList)
        return request.bodyNullable ?: emptyList()
    }
}