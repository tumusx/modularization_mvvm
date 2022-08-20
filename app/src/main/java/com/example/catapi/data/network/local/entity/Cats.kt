package com.example.catapi.data.network.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cats(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_cat") var idCat: Long? = 0,
    @ColumnInfo(name = "name_cat") var nameCat: String? = "",
    @ColumnInfo(name = "description_cat") var descriptionCat: String = "",
)