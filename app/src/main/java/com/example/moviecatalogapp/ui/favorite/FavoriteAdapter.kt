package com.example.moviecatalogapp.ui.favorite

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
import com.example.moviecatalogapp.R
import com.example.moviecatalogapp.models.Movie
import com.example.moviecatalogapp.ui.movie.MovieAdapter
import com.example.moviecatalogapp.ui.movie.MovieViewModel
import kotlinx.android.synthetic.main.movie_item.view.*

class FavoriteAdapter(): RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    private var movieList = emptyList<FavoriteMovie>()

    class FavoriteViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private lateinit var mMovieViewModel: MovieViewModel
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
        val favorite: CheckBox = view.findViewById(R.id.toggleFavorite)
        val bundle = Bundle()

        fun bindMovie(favoriteMovie: FavoriteMovie) {
            itemView.movie_title.text = favoriteMovie.title
            itemView.movie_release_date.text = favoriteMovie.release_date
            Glide.with(itemView).load(IMAGE_BASE + favoriteMovie.poster_path).into(itemView.movie_poster)
            //Log.d("MyLog", "gege " + checking)
            bundle.putString("id", favoriteMovie.id_movie)
            itemView.setOnClickListener {
                itemView.findNavController()
                    .navigate(R.id.action_navigation_main_to_navigation_details, bundle)
            }
            favorite.setOnCheckedChangeListener{ buttonView, isChecked ->
                if (isChecked){
                   // val mMovie= FavoriteMovie(favoriteMovie.id,movie.title,movie.poster,movie.release)
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

    override fun onBindViewHolder(holder: FavoriteAdapter.FavoriteViewHolder, position: Int) {
        holder.bindMovie(movieList.get(position))
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteAdapter.FavoriteViewHolder {
        return FavoriteAdapter.FavoriteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        )
    }

    fun setData(movie: List<FavoriteMovie>){
        this.movieList = movie
        notifyDataSetChanged()
    }
}