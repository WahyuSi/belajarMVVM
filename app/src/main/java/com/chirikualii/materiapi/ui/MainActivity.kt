package com.chirikualii.materiapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.chirikualii.materiapi.R
import com.chirikualii.materiapi.data.dummy.DataDummy
import com.chirikualii.materiapi.data.model.Movie
import com.chirikualii.materiapi.data.remote.ApiClient
import com.chirikualii.materiapi.databinding.ActivityMainBinding
import com.chirikualii.materiapi.ui.adapter.MainViewModel
import com.chirikualii.materiapi.ui.adapter.MovieListAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding :ActivityMainBinding
    private lateinit var adapter: MovieListAdapter

    private val mViewModel : MainViewModel by viewModels (
        factoryProducer = {MainViewModelFactory()}
            )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adapter = MovieListAdapter()
        binding.rvMovie.adapter = adapter


        mViewModel.doGetPopularMovie()
        observeView()
    }

    private fun observeView() {
        mViewModel.listMovie.observe(this){
            adapter.addItem(it)
    }
        mViewModel.isLoading.observe(this){
            if (it){
                binding.progressBar.visibility = View.VISIBLE
            }
            else{
                binding.progressBar.visibility = View.INVISIBLE
            }
        }


}

  }