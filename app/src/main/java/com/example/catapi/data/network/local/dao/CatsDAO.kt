package com.example.catapi.data.network.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.catapi.data.network.local.entity.Cats

@Dao
interface CatsDAO {

    @Insert
    fun insertCats(vararg cats: Cats)

    @Query("SELECT * FROM cats")
    fun selectAllCats(): List<Cats>
}