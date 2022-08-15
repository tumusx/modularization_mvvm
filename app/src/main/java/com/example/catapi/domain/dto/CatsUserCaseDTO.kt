package com.example.catapi.domain.dto

import com.example.catapi.data.dto.Image
import com.example.catapi.data.dto.Weight

data class CatsUserCaseDTO(
    val image: Image?,
    val name: String?,
    val description: String,
    val idCat: String?,
    val wikipedia_url: String?
)
