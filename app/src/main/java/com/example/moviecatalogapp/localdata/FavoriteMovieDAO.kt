package com.example.moviecatalog.localdata

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteMovieDAO {
    @Insert
    suspend fun addToFavorite(favoriteMovie: FavoriteMovie)

    @Query("SELECT * FROM favorite_movie ORDER BY id ASC")
    fun getMovie(): LiveData<List<FavoriteMovie>>

    @Query("DELETE FROM favorite_movie WHERE favorite_movie.id_movie = :id")
    suspend fun removeFromFavorite(id: String): Int

}