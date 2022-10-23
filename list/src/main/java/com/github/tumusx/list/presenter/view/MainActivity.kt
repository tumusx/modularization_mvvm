package com.github.tumusx.list.presenter.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.tumusx.list.R
import com.github.tumusx.list.databinding.ActivityMainBinding
import com.github.tumusx.list.domain.vo.CatsUserCaseDTO
import com.github.tumusx.list.presenter.CatsState
import com.github.tumusx.list.presenter.adapter.CatsAdapter
import com.github.tumusx.list.presenter.viewModel.CatsViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: CatsViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

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
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }

    }

    private fun getCats() {
        viewModel.getCats()
    }

    private fun configRecycler(listCats: List<CatsUserCaseDTO>) {
        val myAdapter = CatsAdapter()
        myAdapter.updateList(listCats)

        binding.recycler.apply {
            adapter = myAdapter
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun configureLoadingList() {
        var visibilityProgressBar = View.VISIBLE
        lifecycleScope.launch {
            viewModel.state.collect {
                when (val state = viewModel.state.value) {
                    is CatsState.SuccessCats -> {
                        configRecycler(state.catsList)
                        visibilityProgressBar = View.GONE
                    }

                    is CatsState.ErrorCats -> {
                        Toast.makeText(
                            this@MainActivity,
                            state.messageError,
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        visibilityProgressBar = View.GONE
                    }

                    is CatsState.IsLoadingCats -> {
                        visibilityProgressBar = View.VISIBLE
                    }
                }
            }
        }
        binding.progressCircular.visibility = visibilityProgressBar
    }
}
