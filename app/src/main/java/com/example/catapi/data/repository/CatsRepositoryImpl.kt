package com.example.catapi.data.repository

import com.example.catapi.data.api.CatsApi
import com.example.catapi.data.dto.CatsDTOItem
import com.example.catapi.domain.repository.CatsRepository
import javax.inject.Inject

class CatsRepositoryImpl @Inject constructor(
    private val api: CatsApi
) : CatsRepository {
    override suspend fun getCats(): List<CatsDTOItem> {
        return api.getCats()
    }

}