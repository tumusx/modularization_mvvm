package com.example.catapi.domain.userCase

import com.example.catapi.common.Resource
import com.example.catapi.domain.dto.CatsUserCaseDTO
import kotlinx.coroutines.flow.Flow

interface CatsUseCase {
    suspend fun getCatsAll(): Flow<Resource<List<CatsUserCaseDTO>>>
}
