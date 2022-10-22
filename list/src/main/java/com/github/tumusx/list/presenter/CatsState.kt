package com.github.tumusx.list.presenter

import com.github.tumusx.list.domain.vo.CatsUserCaseDTO

sealed class CatsState {
    data class SuccessCats(var catsList: List<CatsUserCaseDTO> = emptyList()) : CatsState()
    data class ErrorCats(var messageError: String = "") : CatsState()
    object IsLoadingCats : CatsState()
}