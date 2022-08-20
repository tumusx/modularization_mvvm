package com.example.catapi.presenter.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catapi.R
import com.example.catapi.common.Resource
import com.example.catapi.data.network.local.entity.Cats
import com.example.catapi.databinding.ActivityMainBinding
import com.example.catapi.presenter.adapter.CatsAdapter
import com.example.catapi.presenter.viewModel.CatsViewModel
import com.example.catapi.presenter.viewModel.FavoriteStateCatsViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: CatsViewModel by viewModel()
    private val favoriteStateCatsViewModel: FavoriteStateCatsViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        getCats()
        configureLoadingList()
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_save, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_save -> {
                startActivity(Intent(this, ListSaveCatsActivity::class.java))
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }

    }

    private fun getCats() {
        favoriteStateCatsViewModel.buscarGatosSalvos()
        Log.e("BUSCANDO", favoriteStateCatsViewModel.buscarGatosSalvos().toString())
        viewModel.getCats()
    }

    private fun configRecycler() {
        val myAdapter = CatsAdapter { catsItem ->
            val cats = Cats()
            cats.nameCat = catsItem.name
            cats.descriptionCat = catsItem.description
            favoriteStateCatsViewModel.saveCats(cats)
            Log.d("cats: ", catsItem.toString())
        }
        binding.recycler.apply {
            adapter = myAdapter
            viewModel.state.value?.cats?.let { myAdapter.updateList(it) }
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun configureLoadingList() {
        lifecycleScope.launch {
            delay(2000)
            configRecycler()
        }
    }
}
