package com.example.catapi.domain.userCase

import com.example.catapi.common.Resource
import com.example.catapi.data.dto.getCats
import com.example.catapi.domain.dto.CatsUserCaseDTO
import com.example.catapi.domain.repository.CatsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CatsUserCase @Inject constructor(private val repository: CatsRepository) {

    operator fun invoke(): Flow<Resource<List<CatsUserCaseDTO>>> = flow {
        try {
            emit(Resource.Loading<List<CatsUserCaseDTO>>())
            val cats = repository.getCats().map { it.getCats() }
            emit(Resource.Success<List<CatsUserCaseDTO>>(cats))
        } catch (exception: Exception) {
            emit(Resource.Error<List<CatsUserCaseDTO>>(exception.message ?: "ERROR"))
        }
    }
}