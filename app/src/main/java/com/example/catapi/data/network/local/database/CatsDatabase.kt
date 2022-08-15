package com.example.catapi.data.network.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.catapi.data.network.local.dao.CatsDAO
import com.example.catapi.data.network.local.entity.Cats

@Database(entities = [Cats::class], version = 1)
abstract class CatsDatabase : RoomDatabase() {
    abstract fun catsDao(): CatsDAO
}