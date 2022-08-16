package com.example.catapi.presenter.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catapi.databinding.ActivityMainBinding
import com.example.catapi.presenter.adapter.CatsAdapter
import com.example.catapi.presenter.viewModel.CatsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: CatsViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        getCats()
        configureLoadingList()
        setContentView(binding.root)
    }
    private fun getCats() {
        viewModel.getCats()
    }

    private fun configRecycler() {
        val myAdapter = CatsAdapter {catsItem->
            Log.d("cats: ", catsItem.toString())
        }
        binding.recycler.apply {
            adapter = myAdapter
            myAdapter.updateList(viewModel.state.value.cats)
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun configureLoadingList() {
        lifecycleScope.launch {
            delay(2000)
            configRecycler()
        }
    }
}
