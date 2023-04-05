package com.example.rickandmorty.util.network

import com.rickandmortyapi.util.Jsons
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkLayer {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create(Jsons.provider()))
        .build()

    private val rickAndMortyService: RickAndMortyService by lazy { retrofit.create(RickAndMortyService::class.java) }

    val apiClient = ApiClient(rickAndMortyService)

}