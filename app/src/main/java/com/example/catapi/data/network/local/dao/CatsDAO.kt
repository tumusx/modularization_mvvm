package com.example.catapi.data.network.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.catapi.data.network.local.entity.Cats

@Dao
interface CatsDAO {

    @Insert
    fun insertCats(vararg cats: Cats)

    @Query("SELECT * FROM cats")
    suspend fun selectAllCats(): List<Cats>

    @Delete
    suspend fun deleteCatById(cat: Cats)
}