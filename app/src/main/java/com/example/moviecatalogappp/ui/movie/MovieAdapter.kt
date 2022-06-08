package com.example.moviecatalogappp.ui.movie

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecatalogappp.models.FavoriteMovie
import com.example.moviecatalogappp.R
import com.example.moviecatalogappp.models.Movie
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.movie_item.view.*


class MovieAdapter(
    private val movies: List<Movie>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){
    class MovieViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
        val favorite: ImageButton = view.findViewById(R.id.fav)
        val bundle = Bundle()
        val database = Firebase.database
        var user = FirebaseAuth.getInstance().currentUser
        var userid = user!!.uid
        val favMovie = database.getReference("Movies")
        fun bindMovie(movie: Movie, context: Context) {
            itemView.movie_title.text = movie.title
            itemView.movie_release_date.text = movie.release
            Glide.with(itemView).load(IMAGE_BASE + movie.poster).into(itemView.movie_poster)
            bundle.putString("id", movie.id)
            itemView.setOnClickListener {
                itemView.findNavController()
                    .navigate(R.id.action_navigation_main_to_navigation_details, bundle)
            }
            favorite.setOnClickListener {
                val mMovie= FavoriteMovie(userid,movie.id,movie.title,movie.poster,movie.release)
                    favMovie.child(movie.id).setValue(mMovie)
                Toast.makeText(context,"Фильм добавлен в избранное", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false))
    }
    override fun getItemCount(): Int = movies.size
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        var context:Context=holder.itemView.context
        holder.bindMovie(movies.get(position),context)
        }
    }
