package com.example.catapi.domain.repository

import com.example.catapi.data.dto.CatsDTOItem

interface CatsRepository {
    suspend fun getCats(): List<CatsDTOItem>
}