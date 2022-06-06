package com.example.moviecatalogappp.ui.movie

import android.os.Bundle
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.movie_item.view.*


class MovieAdapter(
    private val movies : List<Movie>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    class MovieViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private lateinit var mMovieViewModel: MovieViewModel
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
        val favorite: CheckBox = view.findViewById(R.id.toggleFavorite)
        val bundle = Bundle()
        val database = Firebase.database
        var user = FirebaseAuth.getInstance().currentUser
        var userid = user!!.uid
        val favMovie = database.getReference("Movies")
        fun bindMovie(movie: Movie) {
            itemView.movie_title.text = movie.title
            itemView.movie_release_date.text = movie.release
            Glide.with(itemView).load(IMAGE_BASE + movie.poster).into(itemView.movie_poster)
            bundle.putString("id", movie.id)
            itemView.setOnClickListener {
                itemView.findNavController()
                    .navigate(R.id.action_navigation_main_to_navigation_details, bundle)
            }
            favorite.setOnCheckedChangeListener{ buttonView, isChecked ->
                val mMovie= FavoriteMovie(userid,movie.id,movie.title,movie.poster,movie.release)
                if (isChecked){
                  // Log.d("MyLog", "gege " )
                  favMovie.child(movie.id).setValue(mMovie)
                   //addToFavorite(mMovie)
                }
              else{
                    favMovie.child(movie.id).removeValue()
              }
            }
        }
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
