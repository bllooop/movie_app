package com.example.moviecatalog.localdata

import androidx.lifecycle.LiveData
import javax.inject.Inject

class FavoriteMovieRepository @Inject constructor(
    private val favoriteMovieDAO: FavoriteMovieDAO
)
{
     val readAllData: LiveData<List<FavoriteMovie>> = favoriteMovieDAO.getMovie()
    suspend fun addToFavorite(favoriteMovie: FavoriteMovie) =
        favoriteMovieDAO.addToFavorite(favoriteMovie)
   // suspend fun checkMovie(id: String) = favoriteMovieDAO.checkMovie(id)
    suspend fun removeFromFavorite(id: String)=
        favoriteMovieDAO.removeFromFavorite(id)
}