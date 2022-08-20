package com.example.catapi.presenter.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapi.data.network.local.entity.Cats
import com.example.catapi.domain.userCase.SaveCatsUseCaseImpl
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class FavoriteStateCatsViewModel(private val saveCatsUseCaseImpl: SaveCatsUseCaseImpl) :
    ViewModel() {
    fun buscarGatosSalvos() = saveCatsUseCaseImpl.getAllCats()
    fun saveCats(cat: Cats) {
        viewModelScope.launch {
            saveCatsUseCaseImpl.saveCats(cat).launchIn(viewModelScope)
        }
    }
}