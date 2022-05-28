package com.example.moviecatalogapp.ui.movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.example.moviecatalogapp.R
import com.example.moviecatalogapp.models.Movie
import com.example.moviecatalogapp.ui.movie.MovieCatalogFragment
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter(
    private val movies : List<Movie>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){


    class MovieViewHolder(view : View) : RecyclerView.ViewHolder(view){
       private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"
        val bundle = Bundle()
        fun bindMovie(movie : Movie){
            itemView.movie_title.text = movie.title
            itemView.movie_release_date.text = movie.release
            Glide.with(itemView).load(IMAGE_BASE + movie.poster).into(itemView.movie_poster)
       //  Glide.with(itemView).load(movie.poster).into(itemView.movie_poster)
            //Log.d("MyLog", "gege " + checking)
            bundle.putString("id",movie.id)
            itemView.setOnClickListener {
                itemView.findNavController().navigate(R.id.action_navigation_main_to_navigation_details,bundle)
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
