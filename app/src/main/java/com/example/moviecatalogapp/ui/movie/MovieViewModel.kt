package com.example.moviecatalogapp.ui.movie

import android.app.Application
import android.util.Log
import android.widget.CheckBox
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecatalog.localdata.FavoriteMovie
import com.example.moviecatalog.localdata.FavoriteMovieDatabase
import com.example.moviecatalog.localdata.FavoriteMovieRepository
import com.example.moviecatalogapp.R
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
   //  private val repository: FavoriteMovieRepository
   /*  init{
          val  favoriteMovieDao = FavoriteMovieDatabase.getAppDatabase(application)!!.getFavoriteMovieDao()
          repository = FavoriteMovieRepository(favoriteMovieDao)
          moviess=MutableLiveData()
     } */


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
   /*  fun addToFavorite(favoriteMovie: FavoriteMovie) {
          viewModelScope.launch(Dispatchers.IO) {
               repository.addToFavorite(favoriteMovie)
          }
          }

*/
     }
