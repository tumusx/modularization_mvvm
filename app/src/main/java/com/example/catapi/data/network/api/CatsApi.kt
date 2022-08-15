package com.example.catapi.data.network.api

import com.example.catapi.data.dto.CatsDTOItem
import retrofit2.Response
import retrofit2.http.GET

interface CatsApi {
    @GET("/v1/breeds")
    suspend fun getCats() : List<CatsDTOItem>
}