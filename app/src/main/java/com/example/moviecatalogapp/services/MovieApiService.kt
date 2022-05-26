package com.example.moviecatalogapp.services

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.moviecatalog.localdata.FavoriteMovieDatabase
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

class MovieApiService {

    companion object{
        private const val BASE_URL = "https://api.themoviedb.org"
       // private const val BASE_URL = "https://kinopoiskapiunofficial.tech/api/v2.2/films"
        private var retrofit : Retrofit? = null

        fun getInstance() : Retrofit{
            if(retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
        @Provides
        @Singleton
        fun provideFavMovieDatabase(
            @ApplicationContext app: Context
        )= Room.databaseBuilder(
            app,
            FavoriteMovieDatabase::class.java,
            "movie_db"
        ).build()
        @Provides
        @Singleton
        fun provideFavMovieDao(db: FavoriteMovieDatabase) = db.getFavoriteMovieDao()
    }
}