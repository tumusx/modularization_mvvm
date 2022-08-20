package com.example.catapi.data.repository

import com.example.catapi.data.network.local.database.CatsDatabase
import com.example.catapi.data.network.local.entity.Cats

class SaveCatsRepository(private val catsDatabase: CatsDatabase) {
    fun saveCats(cats: Cats) {
        catsDatabase.catsDao().insertCats(cats)
    }

    fun getCatsAll() : List<Cats> = catsDatabase.catsDao().selectAllCats()
}