package com.example.catapi.domain.userCase

import com.example.catapi.common.Resource
import com.example.catapi.data.dto.getCats
import com.example.catapi.data.repository.CatsRepositoryImpl
import com.example.catapi.domain.dto.CatsUserCaseDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CatsUserCaseImpl(private val repository: CatsRepositoryImpl) : CatsUseCase {
    override suspend fun getCatsAll(): Flow<Resource<List<CatsUserCaseDTO>>> = flow {
        try {
            emit(Resource.Loading<List<CatsUserCaseDTO>>())
            val cats = repository.getCats().map { it.getCats() }
            emit(Resource.Success<List<CatsUserCaseDTO>>(cats))
        } catch (exception: Exception) {
            emit(Resource.Error<List<CatsUserCaseDTO>>(exception.message ?: "ERROR"))
        }
    }
}