package com.example.rickandmorty.util.network

import com.example.rickandmorty.struct.Characters
import com.example.rickandmorty.struct.Locations
import com.rickandmortyapi.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyService {

    @GET("character/")
    suspend fun getCharactersAtPage(@Query("page") id: Int): Response<Characters>

    @GET("location/")
    suspend fun getLocationsAtPage(@Query("page") id: Int): Response<Locations>

    @GET("character/{list}")
    suspend fun getMultipleCharacters(@Path("list") characterList: List<String>): Response<List<Character>>

}