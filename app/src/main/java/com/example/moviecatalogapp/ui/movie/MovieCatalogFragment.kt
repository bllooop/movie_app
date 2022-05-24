package com.example.moviecatalog.ui.movie

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatalogapp.R
import com.example.moviecatalogapp.databinding.FragmentMainBinding
import com.example.moviecatalog.models.Movie
import com.example.moviecatalog.models.MovieResponse
import com.example.moviecatalog.services.MovieApiInterface
import com.example.moviecatalog.services.MovieApiService
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.movie_item.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList


class MovieCatalogFragment : Fragment() {
    //private val args by navArgs<MovieCatalogFragmentArgs>()
  //  private lateinit var tempArrayList : ArrayList<Movie>

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

  //  private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
     //  val root: View = binding.root
       // return root
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

   override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
       super.onViewCreated(itemView, savedInstanceState)
       val viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
       viewModel.getLiveDataObserver().observe(this, Observer {
           rv_movies_list.layoutManager = LinearLayoutManager(activity)
           rv_movies_list.setHasFixedSize(true)
           //  getMovieData { movies: List<Movie> ->
           rv_movies_list.adapter = MovieAdapter(it)
           //tempArrayList = arrayListOf()
         //  tempArrayList.addAll(it)
           // }
       })
      viewModel.getMovieData()
      /* var _isChecked = false
       CoroutineScope(Dispatchers.IO).launch {
           val count = viewModel.checkMovie(movie.id)
           withContext(Main) {
               if (count > 0) {
                   toggleFavorite.isChecked = true
                   _isChecked = true
               } else {
                   toggleFavorite.isChecked = false
                   _isChecked = false
               }
           }

           toggleFavorite.setOnClickListener {
               _isChecked = !_isChecked
               if (_isChecked){
                   viewModel.addToFavorite(movie)
               } else{
                   viewModel.removeFromFavorite(movie.id)
               }
               toggleFavorite.isChecked = _isChecked
           }
       }*/

   }
  /* private fun getMovieData(callback: (List<Movie>) -> Unit){
        val apiService = MovieApiService.getInstance().create(MovieApiInterface::class.java)
        apiService.getMovieList().enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {}

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                 return callback(response.body()!!.movies)
            }
        })
    } */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

   /* override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        val item = menu?.findItem(R.id.search_action)
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean{
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean{
                tempArrayList.clear()
                val searchText= newText!!.toLowerCase(Locale.getDefault())
                if(searchText.isNotEmpty()){
                    tempArrayList.forEach{
                          //  if(it.heading.toLowerCase(Locale.getDefault().contains(searchText))){
                                    tempArrayList.add(it)
                           // }
                    }
                    rv_movies_list.adapter!!.notifyDataSetChanged()
                }
                 else {
                    tempArrayList.clear()
                    rv_movies_list.adapter!!.notifyDataSetChanged()
                }
                return true
            }
        })
        return super.onCreateOptionsMenu(menu, inflater)
    } */
}