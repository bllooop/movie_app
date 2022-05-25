package com.example.moviecatalogapp.ui.movie

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalog.models.Movie
import com.example.moviecatalog.ui.movie.MovieAdapter
import com.example.moviecatalog.ui.movie.MovieViewModel
import com.example.moviecatalogapp.R
import com.example.moviecatalogapp.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.*
import java.util.*
import kotlin.collections.ArrayList


class MovieCatalogFragment() : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    val arrayList = ArrayList<Movie>()
    val displayList = ArrayList<Movie>()
  //  private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


   override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
       super.onViewCreated(itemView, savedInstanceState)
       val viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
       viewModel.getLiveDataObserver().observe(this, Observer {
           rv_movies_list.layoutManager = LinearLayoutManager(activity)
           rv_movies_list.setHasFixedSize(true)
           //  getMovieData { movies: List<Movie> ->
           arrayList.addAll(it)
           displayList.addAll(arrayList)
           rv_movies_list.adapter = MovieAdapter(displayList)
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

   override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu,menu)
           val item = menu!!.findItem(R.id.search_action)
        if (item!=null) {
            val searchView = item?.actionView as SearchView
            val viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
           // viewModel.getMovieData()
            viewModel.getLiveDataObserver().observe(this, Observer {
                //   displayList.addAll(it)
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        if (newText!!.isNotEmpty()) {
                            displayList.clear()
                            val search = newText.toLowerCase(Locale.getDefault())
                            arrayList.forEach {
                                if (it.title.toLowerCase(Locale.getDefault()).contains(search)) {
                                    displayList.add(it)
                                    Log.d("MyLog", "gege " + it.toString())
                                }
                            }
                            rv_movies_list.adapter!!.notifyDataSetChanged()
                        } else {
                            displayList.clear()
                            displayList.addAll(arrayList)
                            rv_movies_list.adapter!!.notifyDataSetChanged()
                        }
                        return true
                    }
                })
            })
        }
        return super.onCreateOptionsMenu(menu, inflater)
    }
}