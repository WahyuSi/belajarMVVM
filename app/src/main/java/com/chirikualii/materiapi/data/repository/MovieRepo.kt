package com.chirikualii.materiapi.data.repository

import com.chirikualii.materiapi.data.model.Movie

interface MovieRepo {
    fun getPopularMovie() : List<Movie>
}