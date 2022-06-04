package com.example.moviecatalogapp.ui.favorite

import android.app.Application
import androidx.lifecycle.*
import com.example.moviecatalog.localdata.FavoriteMovie
import com.example.moviecatalog.localdata.FavoriteMovieDAO
import com.example.moviecatalog.localdata.FavoriteMovieDatabase
import com.example.moviecatalog.localdata.FavoriteMovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : AndroidViewModel(application)  {
    val allMovies : LiveData<List<FavoriteMovie>>
    private val repository: FavoriteMovieRepository
    init{
        val  favoriteMovieDao = FavoriteMovieDatabase.getAppDatabase(application)!!.getFavoriteMovieDao()
        repository = FavoriteMovieRepository(favoriteMovieDao)
        allMovies = repository.readAllData
    }
   suspend fun getAllMoviesObservers():LiveData<List<FavoriteMovie>>{
        return allMovies
    }


}