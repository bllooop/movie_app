package com.example.moviecatalogappp.ui.favorite

import android.app.Application
import androidx.lifecycle.*
import com.example.moviecatalogappp.models.FavoriteMovie
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FavoriteViewModel(application: Application) : AndroidViewModel(application)  {
    var moviess = MutableLiveData<List<FavoriteMovie>>()
    private lateinit var movieArrayList:ArrayList<FavoriteMovie>

    fun getLiveDataObserver():MutableLiveData<List<FavoriteMovie>>{
        movieArrayList= arrayListOf<FavoriteMovie>()
        val y:ArrayList<FavoriteMovie> = getMovie()
        moviess.postValue(y)
        return moviess
    }

    fun getMovie(): ArrayList<FavoriteMovie> {

        val database = Firebase.database
        val favMovie = database.getReference("Movies")
        val addValueEventListener = favMovie.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (moviesSnapshot in snapshot.children) {
                        val mMovie = moviesSnapshot.getValue(FavoriteMovie::class.java)
                        movieArrayList.add(mMovie!!)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
        return movieArrayList
    }

}