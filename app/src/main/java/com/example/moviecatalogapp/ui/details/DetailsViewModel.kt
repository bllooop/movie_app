package com.example.moviecatalogapp.ui.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogapp.models.MovieDetails
import com.example.moviecatalogapp.models.MovieResponse
import com.example.moviecatalogapp.services.MovieApiInterface
import com.example.moviecatalogapp.services.MovieApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel : ViewModel() {
    var movie_detail = MutableLiveData<MovieDetails>()

    init {
        movie_detail=MutableLiveData()
    }
    fun getSingleMovieData(id: Int):LiveData<MovieDetails>{
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMovieDetails().enqueue(object : Callback<MovieDetails> {
            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {}
            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {

                 movie_detail.postValue(response.body())
            }
        })
        return movie_detail
    }

    fun getLiveDataObserver(): MutableLiveData<MovieDetails> {
        return movie_detail
    }}