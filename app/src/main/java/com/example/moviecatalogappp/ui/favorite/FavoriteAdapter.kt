package com.example.moviecatalogappp.ui.favorite

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
import com.example.moviecatalogappp.R
import com.example.moviecatalogappp.models.Movie
import com.example.moviecatalogappp.ui.movie.MovieAdapter
import com.example.moviecatalogappp.ui.movie.MovieViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.movie_item.view.*

class FavoriteAdapter( private val movies : List<FavoriteMovie>): RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    private var movieList = emptyList<FavoriteMovie>()

    class FavoriteViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private lateinit var mMovieViewModel: MovieViewModel
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
        val favorite: CheckBox = view.findViewById(R.id.toggleFavorite)
        val bundle = Bundle()
        val database = Firebase.database
        val favMovie = database.getReference("Movies")

        fun bindMovie(favoriteMovie: FavoriteMovie) {
            itemView.movie_title.text = favoriteMovie.title
            itemView.movie_release_date.text = favoriteMovie.release_date
            Glide.with(itemView).load(IMAGE_BASE + favoriteMovie.poster_path).into(itemView.movie_poster)
            //Log.d("MyLog", "gege " + checking)
            bundle.putString("id", favoriteMovie.id_movie)
            itemView.setOnClickListener {
                itemView.findNavController()
                    .navigate(R.id.action_navigation_favorite_to_navigation_details, bundle)
            }
            favorite.setOnCheckedChangeListener{ buttonView, isChecked ->
                if (isChecked){
                    //Log.d("MyLog", "gege " )
                } else{
                        favMovie.child(favoriteMovie.id_movie).removeValue()
                    }
                 }
            }
        }


    override fun onBindViewHolder(holder: FavoriteAdapter.FavoriteViewHolder, position: Int) {
       holder.bindMovie(movies.get(position))
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteAdapter.FavoriteViewHolder {
        return FavoriteAdapter.FavoriteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item_fav, parent, false)
        )
    }

    fun setData(movie: List<FavoriteMovie>){
        this.movieList = movie
        notifyDataSetChanged()
    }
}