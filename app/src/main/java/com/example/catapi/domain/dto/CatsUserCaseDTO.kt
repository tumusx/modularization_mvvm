package com.example.catapi.domain.dto

import com.example.catapi.data.dto.Image
import com.example.catapi.data.dto.Weight

data class CatsUserCaseDTO(
    val image: Image?,
    val structCat: Weight?,
    val name: String?,
    val rare: Int?
)
