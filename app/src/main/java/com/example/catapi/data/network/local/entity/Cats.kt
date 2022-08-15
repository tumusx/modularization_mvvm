package com.example.catapi.data.network.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cats(
    @PrimaryKey val idCat: Long? = null,
    @ColumnInfo(name = "name_cat") val nameCat: String? = "",
    @ColumnInfo(name = "description_cat") val descriptionCat: String = "",
)