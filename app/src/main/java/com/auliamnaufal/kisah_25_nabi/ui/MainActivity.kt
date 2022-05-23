package com.auliamnaufal.kisah_25_nabi.ui

import KisahAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.GridLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.auliamnaufal.kisah_25_nabi.R
import com.auliamnaufal.kisah_25_nabi.data.KisahResponse
import com.auliamnaufal.kisah_25_nabi.databinding.ActivityMainBinding
import com.auliamnaufal.kisah_25_nabi.ui.detail.DetailActivity

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    private var _viewModel: MainViewModel? = null
    private val viewModel get() = _viewModel as MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.getKisahnabi()

        viewModel.isLoading.observe(this) { showLoading(it) }
        viewModel.isError.observe(this) { showError(it) }
        viewModel.kisahResponse.observe(this) { showData(it) }


    }

    private fun showData(data: List<KisahResponse>?) {
        binding.recyclerMain.apply {
            val mAdapter = KisahAdapter()
            mAdapter.setData(data)
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = mAdapter
            mAdapter.setOnItemClickCallback(object  : OnItemClickCallback {
                override fun onItemClicked(item: KisahResponse) {
                    startActivity(
                        Intent(this@MainActivity, DetailActivity::class.java)
                            .putExtra(DetailActivity.EXTRA_DATA, item)
                    )
                }
            })
        }
    }

    private fun showError(isError: Throwable?) {
        Log.e("MainActivity", "Error get data: $isError", )
    }

    private fun showLoading(isLoading: Boolean?) {
        if (isLoading == true) {
            binding.progressMain.visibility = View.VISIBLE
        } else {
            binding.progressMain.visibility = View.INVISIBLE
        }
    }
}