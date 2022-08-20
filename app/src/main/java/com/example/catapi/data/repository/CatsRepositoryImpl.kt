package com.example.catapi.data.repository

import com.example.catapi.data.dto.CatsDTOItem
import com.example.catapi.data.network.api.CatsApi
import com.example.catapi.domain.repository.CatsRepository

class CatsRepositoryImpl(
    private val api: CatsApi
) : CatsRepository {
    override suspend fun getCats(): List<CatsDTOItem> {
        return api.getCats()
    }

    override suspend fun getCatById(idCat: String): CatsDTOItem {
        TODO("Not yet implemented")
    }

}