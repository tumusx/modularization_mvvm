package com.github.tumusx.list.domain.vo

import com.github.tumusx.list.data.dto.Image

data class CatsUserCaseDTO(
    val image: Image?,
    val name: String?,
    val description: String,
    val idCat: String?,
    val wikipedia_url: String?,
    var catsFavorite: Boolean? = false
)
