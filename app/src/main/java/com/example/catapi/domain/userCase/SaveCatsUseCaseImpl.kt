package com.example.catapi.domain.userCase

import com.example.catapi.data.network.local.entity.Cats
import com.example.catapi.data.repository.SaveCatsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SaveCatsUseCaseImpl(private val saveCatsRepository: SaveCatsRepository) : SaveCatsUseCase {
    override suspend fun saveCats(cats: Cats): Flow<Cats> = flow {
        try {
            saveCatsRepository.saveCats(cats)
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }

    fun getAllCats(): List<Cats> {
        return try {
            saveCatsRepository.getCatsAll()
        } catch (exception: Exception) {
            exception.printStackTrace()
            emptyList<Cats>()
        }
    }
}