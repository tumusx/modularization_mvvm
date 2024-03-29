package com.github.tumusx.list.service

import com.github.tumusx.list.data.dto.CatsDTOItem
import retrofit2.http.GET

interface CatsApi {
    @GET("/v1/breeds")
    suspend fun getCats() : List<CatsDTOItem>
}

