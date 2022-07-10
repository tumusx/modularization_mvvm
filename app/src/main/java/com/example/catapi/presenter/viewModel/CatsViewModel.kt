package com.example.catapi.presenter.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapi.common.Resource
import com.example.catapi.domain.userCase.CatsUserCase
import com.example.catapi.presenter.view.CatsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CatsViewModel @Inject constructor(private val getCatsUserCase: CatsUserCase) : ViewModel() {

    private val _state = mutableStateOf(CatsState())
    val state: State<CatsState> = _state

    init {
        getCats()

    }

    fun getCats() {
        getCatsUserCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CatsState(cats = result.dataResult ?: emptyList())
                    Log.d("top", state.value.cats.toString())
                }
                is Resource.Loading -> {
                    _state.value = CatsState(isLoading = true)
                }
                is Resource.Error -> {
                    _state.value = CatsState(error = result.message ?: "ERROR")
                }
            }
        }.launchIn(viewModelScope)
    }
}