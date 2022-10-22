package com.github.tumusx.list.domain.repository

import com.github.tumusx.list.data.dto.CatsDTOItem

interface CatsRepository {
    suspend fun getCats(): List<CatsDTOItem>
    suspend fun getCatById(idCat: String) : CatsDTOItem
}