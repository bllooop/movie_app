package com.example.moviecatalogapp.ui.movie

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecatalog.localdata.FavoriteMovie
import com.example.moviecatalog.localdata.FavoriteMovieDatabase
import com.example.moviecatalog.localdata.FavoriteMovieRepository
import com.example.moviecatalogapp.R
import com.example.moviecatalogapp.models.Movie
import dagger.hilt.android.internal.Contexts.getApplication
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter(
    private val movies : List<Movie>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){
    //private val repository: FavoriteMovieRepository
   // private val application : Application
    init {
       // application = getApplication(context)
       // val  favoriteMovieDao = FavoriteMovieDatabase.getAppDatabase(context)!!.getFavoriteMovieDao()
     //   repository = FavoriteMovieRepository(favoriteMovieDao)
    }


    class MovieViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private lateinit var mMovieViewModel: MovieViewModel
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
        val favorite: CheckBox = view.findViewById(R.id.toggleFavorite)
        val bundle = Bundle()

        fun bindMovie(movie: Movie) {
            itemView.movie_title.text = movie.title
            itemView.movie_release_date.text = movie.release
            Glide.with(itemView).load(IMAGE_BASE + movie.poster).into(itemView.movie_poster)
            //Log.d("MyLog", "gege " + checking)
            bundle.putString("id", movie.id)
            itemView.setOnClickListener {
                itemView.findNavController()
                    .navigate(R.id.action_navigation_main_to_navigation_details, bundle)
            }
            favorite.setOnCheckedChangeListener{ buttonView, isChecked ->
              if (isChecked){
                    val mMovie=FavoriteMovie(movie.id,movie.title,movie.poster,movie.release)
                   Log.d("MyLog", "gege " )
                   //addToFavorite(mMovie)
                }
              //else{
                  //  val mMovie=FavoriteMovie(movie.id,movie.title,movie.poster,movie.release)
                    //mMovieViewModel.removeFromFavorite(id)
               // }
            }
        }
    }
    suspend fun addToFavorite(favoriteMovie: FavoriteMovie) {
        //viewModelScope.launch(Dispatchers.IO) {
     //   repository.addToFavorite(favoriteMovie)
        // }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false))
    }
    override fun getItemCount(): Int = movies.size
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindMovie(movies.get(position))
        }
    }
