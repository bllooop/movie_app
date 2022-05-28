package com.example.moviecatalogapp.ui.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.moviecatalogapp.R
import com.example.moviecatalogapp.models.Movie
import com.example.moviecatalogapp.ui.movie.MovieViewModel
import kotlinx.android.synthetic.main.details_fragment.*
import kotlinx.android.synthetic.main.movie_item.view.*
import com.example.moviecatalogapp.models.MovieDetails
import com.google.gson.annotations.SerializedName


class DetailsFragment : Fragment() {
    private val IMAGE_BASE = "https://image.tmdb.org/t/p/w500/"

    companion object {
        fun newInstance() = DetailsFragment()
    }

    private lateinit var viewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.details_fragment, container, false)
    }
        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            val viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
            viewModel.getSingleMovieData(435346).observe(this, Observer {
                bindMovie(it)
            })
    }
    fun bindMovie(movies: MovieDetails){
        movie_title.text = movies.title
        movie_release_date.text = movies.releaseDate
        movie_runtime.text = movies.runtime.toString()
        movie_status.text = movies.status
        movie_vote_average.text = movies.voteAverage.toString()
        Glide.with(this).load(IMAGE_BASE + movies.posterPath).into(movie_poster)
        // Log.d("MyLog", "gege " + checking)
    }
}


