package com.example.moviecatalogapp.ui.movie

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalog.localdata.FavoriteMovie
import com.example.moviecatalog.localdata.FavoriteMovieRepository
import com.example.moviecatalogapp.models.Movie
import com.example.moviecatalogapp.models.MovieResponse
import com.example.moviecatalogapp.services.MovieApiInterface
import com.example.moviecatalogapp.services.MovieApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel : ViewModel() {
     var moviess = MutableLiveData<List<Movie>>()
     init {
          moviess=MutableLiveData()
     }
   /*  fun addToFavorite(movie: Movie){
          CoroutineScope(Dispatchers.IO).launch {
               repository.addToFavorite(
                    FavoriteMovie(
                         movie.id,
                         movie.title,
                         movie.poster,
                         movie.release
                    )
               )
          }
     } */

     fun getLiveDataObserver():MutableLiveData<List<Movie>>{
          return moviess
     }
    /* suspend fun checkMovie(id: String) = repository.checkMovie(id)
     fun removeFromFavorite(id: String){
          CoroutineScope(Dispatchers.IO).launch {
               repository.removeFromFavorite(id)
          }
     } */

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