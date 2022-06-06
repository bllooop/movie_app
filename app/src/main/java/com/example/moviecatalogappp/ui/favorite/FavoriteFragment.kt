package com.example.moviecatalogappp.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalog.localdata.FavoriteMovie
import com.example.moviecatalogappp.databinding.FragmentFavoriteBinding
import com.example.moviecatalogappp.models.Movie
import com.example.moviecatalogappp.ui.movie.MovieAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_main.*

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var mFavoriteViewModel: FavoriteViewModel
    private lateinit var movieArrayList:ArrayList<FavoriteMovie>
    val displayList = ArrayList<FavoriteMovie>()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setHasOptionsMenu(true)
        //return inflater.inflate(R.layout.fragment_main, container, false)
        return root
    }
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        movieArrayList= arrayListOf<FavoriteMovie>()
        rv_movies_list.layoutManager = LinearLayoutManager(activity)
        val database = Firebase.database
        val favMovie = database.getReference("Movies")
        //mFavoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        //mFavoriteViewModel.getLiveDataObserver().observe(viewLifecycleOwner, Observer {
         favMovie.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (moviesSnapshot in snapshot.children) {
                        val mMovie = moviesSnapshot.getValue(FavoriteMovie::class.java)
                        movieArrayList.add(mMovie!!)
                    }
                    val adapter = FavoriteAdapter(movieArrayList)
                    rv_movies_list.adapter = adapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
       // })
       // mFavoriteViewModel.getMovie()
    }
}