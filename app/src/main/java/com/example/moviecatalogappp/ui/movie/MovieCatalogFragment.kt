package com.example.moviecatalogappp.ui.movie

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalogappp.R
import com.example.moviecatalogappp.databinding.FragmentMainBinding
import com.example.moviecatalogappp.models.Movie
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
        val root: View = binding.root
        setHasOptionsMenu(true)

        //return inflater.inflate(R.layout.fragment_main, container, false)
        return root
    }

   override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
       super.onViewCreated(itemView, savedInstanceState)
       // val secondFragment=DetailsFragment()
       //val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
       val viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
       viewModel.getLiveDataObserver().observe(viewLifecycleOwner, Observer {
           rv_movies_list.layoutManager = LinearLayoutManager(activity)
           rv_movies_list.setHasFixedSize(true)
           //  getMovieData { movies: List<Movie> ->
           arrayList.addAll(it)
           displayList.addAll(arrayList)
           var adapter = MovieAdapter(arrayList)
           rv_movies_list.adapter = adapter
           arrayList.forEach {
           }
       })
       viewModel.getMovieData()
   }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

   override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu,menu)
           val item = menu!!.findItem(R.id.search_action)
        if (item!=null) {
            val searchView = item?.actionView as SearchView
          //  val viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
           // viewModel.getMovieData()
           // viewModel.getLiveDataObserver().observe(this, Observer {
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
                                  //  Log.d("MyLog", "gege " + it.toString())
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
            //})
        }
        return super.onCreateOptionsMenu(menu, inflater)
    }
}