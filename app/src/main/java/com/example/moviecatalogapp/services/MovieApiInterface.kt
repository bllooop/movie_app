package com.example.moviecatalogapp.services

import com.example.moviecatalogapp.models.MovieResponse
import com.example.moviecatalogapp.models.MovieDetails
import retrofit2.Call
import retrofit2.http.GET

interface MovieApiInterface {

    @GET("/3/movie/popular?api_key=9ef9480bcac9a39f085f399caca8e2fb")
    fun getMovieList(): Call<MovieResponse>
    @GET("/3/movie/526896?api_key=9ef9480bcac9a39f085f399caca8e2fb")
    fun getMovieDetails(): Call<MovieDetails>
    @GET("top?type=TOP_250_BEST_FILMS&page=1&X-API-KEY=2583002f-4bb5-44cb-bd5e-ff5113f578fd")
    fun getMovieList2(): Call<MovieResponse>
}