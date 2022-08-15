package com.example.catapi.domain.userCase

import com.example.catapi.data.network.local.entity.Cats
import kotlinx.coroutines.flow.Flow

interface SaveCatsUseCase {
    suspend fun saveCats(cats: Cats): Flow<Cats>
}
