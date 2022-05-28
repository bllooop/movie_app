package com.example.moviecatalogapp.services

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.moviecatalog.localdata.FavoriteMovieDatabase
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

class MovieApiService {

    companion object{
        private const val BASE_URL = "https://api.themoviedb.org"
       // private const val BASE_URL = "https://kinopoiskapiunofficial.tech/api/v2.2/films"
        private var retrofit : Retrofit? = null
        val clientBuilder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()

        fun getInstance() : Retrofit{
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(loggingInterceptor)
            if(retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(clientBuilder.build())
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