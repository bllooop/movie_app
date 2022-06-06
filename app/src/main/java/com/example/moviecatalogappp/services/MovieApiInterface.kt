package com.example.moviecatalogappp.services

import com.example.moviecatalogappp.models.MovieResponse
import com.example.moviecatalogappp.models.MovieDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiInterface {

    @GET("/3/movie/popular?api_key=9ef9480bcac9a39f085f399caca8e2fb")
    fun getMovieList(): Call<MovieResponse>
    @GET("/3/movie/{id}?api_key=9ef9480bcac9a39f085f399caca8e2fb")
    fun getMovieDetails(@Path("id") movie_id: Int): Call<MovieDetails>

    @GET("/3/movie/")
    fun getMovieDetail(@Query("movie_id") movie_id: Int,
                       @Query("api_key") key: String): Call<MovieDetails>

}