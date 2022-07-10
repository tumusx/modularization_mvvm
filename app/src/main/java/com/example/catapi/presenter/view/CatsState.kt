package com.example.catapi.presenter.view

import com.example.catapi.domain.dto.CatsUserCaseDTO

data class CatsState(
    val isLoading: Boolean = false,
    val error: String = "",
    val cats: List<CatsUserCaseDTO> = emptyList()
)