package com.github.tumusx.list.domain.userCase

import com.github.tumusx.list.data.dto.getCats
import com.github.tumusx.list.data.repository.CatsRepositoryImpl
import com.github.tumusx.list.domain.vo.CatsUserCaseDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CatsUserCaseImpl(private val repository: CatsRepositoryImpl) : CatsUseCase {
    override suspend fun getCatsAll(): Flow<com.github.tumusx.shared.resource.Resource<List<CatsUserCaseDTO>>> = flow {
        try {
            emit(com.github.tumusx.shared.resource.Resource.Loading<List<CatsUserCaseDTO>>())
            val cats = repository.getCats().map { it.getCats() }
            emit(com.github.tumusx.shared.resource.Resource.Success<List<CatsUserCaseDTO>>(cats))
        } catch (exception: Exception) {
            emit(com.github.tumusx.shared.resource.Resource.Error<List<CatsUserCaseDTO>>(exception.message ?: "ERROR"))
        }
    }
}