package com.chirikualii.materiapi.ui.adapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirikualii.materiapi.data.model.Movie
import com.chirikualii.materiapi.data.repository.MovieRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel (val repo: MovieRepo): ViewModel (){

    private val _listMovie = MutableLiveData <List<Movie>>()
    val listMovie : LiveData<List<Movie>> =_listMovie

    private  val _isLoding = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoding

    fun doGetPopularMovie(){
        _isLoding.value = true
        viewModelScope.launch (Dispatchers.IO){
            val listData = repo.getPopularMovie()

            withContext(Dispatchers.Main){
                _listMovie.value = listData
                _isLoding.value = false
            }
        }

    }
}