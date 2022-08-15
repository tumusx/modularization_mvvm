package com.example.catapi.domain.userCase

import com.example.catapi.data.network.local.database.CatsDatabase
import com.example.catapi.data.network.local.entity.Cats
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SaveCatsUseCaseImpl @Inject constructor(private val catsDatabase: CatsDatabase) : SaveCatsUseCase {
    override suspend fun saveCats(cats: Cats): Flow<Cats> = flow {
        try {
            val saveCats = catsDatabase.catsDao().insertCats(cats)
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }
}