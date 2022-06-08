package com.example.moviecatalogappp.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogappp.models.Movie
import com.example.moviecatalogappp.models.MovieResponse
import com.example.moviecatalogappp.services.MovieApiInterface
import com.example.moviecatalogappp.services.MovieApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {
     var moviess = MutableLiveData<List<Movie>>()

     fun getLiveDataObserver():MutableLiveData<List<Movie>>{
          return moviess
     }

     //callback: (List<Movie>) -> Unit
     fun getMovieData(){
          val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
          apiService.getMovieList().enqueue(object : Callback<MovieResponse> {
               override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}
               override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                    return moviess.postValue(response.body()!!.movies)
               }
          })
     }
}
