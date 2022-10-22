package com.github.tumusx.list.data.repository

import com.github.tumusx.list.data.dto.CatsDTOItem
import com.github.tumusx.list.domain.repository.CatsRepository
import com.github.tumusx.local.service.CatsApi

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