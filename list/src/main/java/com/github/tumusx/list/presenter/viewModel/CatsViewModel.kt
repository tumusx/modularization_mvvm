package com.github.tumusx.list.presenter.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.tumusx.list.domain.userCase.CatsUserCaseImpl
import com.github.tumusx.list.presenter.CatsState
import com.github.tumusx.shared.resource.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CatsViewModel(private val getCatsUserCase: CatsUserCaseImpl) :
    ViewModel() {
    private val _state: MutableStateFlow<CatsState> = MutableStateFlow(CatsState.IsLoadingCats)
    val state: StateFlow<CatsState> = _state

    init {
        getCats()

    }

    fun getCats() {
        viewModelScope.launch {
            getCatsUserCase.getCatsAll().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = CatsState.SuccessCats(result.dataResult ?: emptyList())
                        Log.e("SUCCESS", result.dataResult?.firstOrNull().toString())
                    }
                    is Resource.Loading -> {
                        _state.value = CatsState.IsLoadingCats
                    }
                    is Resource.Error -> {
                        result.message?.let { Log.e("Error", it) }
                        _state.value = CatsState.ErrorCats(messageError = result.message ?: "ERROR")
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}