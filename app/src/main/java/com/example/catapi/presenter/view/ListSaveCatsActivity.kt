package com.example.catapi.presenter.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.catapi.data.network.local.entity.Cats
import com.example.catapi.databinding.CatSaveListItemDbBinding
import com.example.catapi.presenter.viewModel.FavoriteStateCatsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListSaveCatsActivity : AppCompatActivity() {
    private val viewModelGetSaveItems: FavoriteStateCatsViewModel by viewModel()
    private lateinit var bindingView: CatSaveListItemDbBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingView = CatSaveListItemDbBinding.inflate(layoutInflater)
        setupItemFavorite()
        setContentView(bindingView.root)
    }

    private fun getItemList(): List<Cats> = viewModelGetSaveItems.buscarGatosSalvos()

    private fun setupItemFavorite() {
        val itemCats = Cats()
        getItemList().forEachIndexed { _, cats ->
            itemCats.descriptionCat = cats.descriptionCat
            itemCats.idCat = cats.idCat
            itemCats.nameCat = cats.nameCat
        }
        bindingView.apply {
            txtDescriptionCat.text = itemCats.descriptionCat
            txtNameCat.text = itemCats.nameCat
        }
    }
}