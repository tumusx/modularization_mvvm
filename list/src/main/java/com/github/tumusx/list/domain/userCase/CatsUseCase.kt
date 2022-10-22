package com.github.tumusx.list.domain.userCase

import com.github.tumusx.shared.resource.Resource
import com.github.tumusx.list.domain.vo.CatsUserCaseDTO
import kotlinx.coroutines.flow.Flow

interface CatsUseCase {
    suspend fun getCatsAll(): Flow<Resource<List<CatsUserCaseDTO>>>
}
