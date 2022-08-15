package com.example.catapi.presenter.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapi.data.network.local.entity.Cats
import com.example.catapi.domain.userCase.SaveCatsUseCaseImpl
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteStateCatsViewModel @Inject constructor(private val saveCatsUseCaseImpl: SaveCatsUseCaseImpl) :
    ViewModel() {

    fun saveCats(cat: Cats) = viewModelScope.launch { saveCatsUseCaseImpl.saveCats(cat) }

}