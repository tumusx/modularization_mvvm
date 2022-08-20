package com.example.catapi.presenter.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapi.common.Resource
import com.example.catapi.data.network.local.entity.Cats
import com.example.catapi.domain.userCase.CatsUserCaseImpl
import com.example.catapi.presenter.view.CatsState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CatsViewModel (private val getCatsUserCase: CatsUserCaseImpl) :
    ViewModel() {
    private val _state = MutableLiveData(CatsState())
    val state: LiveData<CatsState> = _state

    init {
        getCats()

    }

    fun getCats() {
        viewModelScope.launch {
            getCatsUserCase.getCatsAll().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = CatsState(cats = result.dataResult ?: emptyList())
                        Log.e("SUCCESS", result.dataResult?.firstOrNull().toString())
                    }
                    is Resource.Loading -> {
                        _state.value = CatsState(isLoading = true)
                    }
                    is Resource.Error -> {
                        result.message?.let { Log.e("Error", it) }
                        _state.value = CatsState(error = result.message ?: "ERROR")
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}