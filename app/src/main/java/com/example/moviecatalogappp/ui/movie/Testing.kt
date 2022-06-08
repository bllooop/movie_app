package com.example.moviecatalogappp.ui.movie
/*import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_main.*

super.onViewCreated(itemView, savedInstanceState)
firebaseAuth = FirebaseAuth.getInstance()
val firebaseUser = firebaseAuth.currentUser
var userid = firebaseUser!!.uid
movieArrayList= arrayListOf<FavoriteMovie>()
//val adapter = FavoriteAdapter(movieArrayList)
//rv_movies_list.adapter = adapter
val database = Firebase.database
val favMovie = database.getReference("Movies")
mFavoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
mFavoriteViewModel.getLiveDataObserver().observe(viewLifecycleOwner, Observer {
    rv_movies_list.layoutManager = LinearLayoutManager(activity)
    rv_movies_list.setHasFixedSize(true)
    arrayList.clear()
    arrayList.addAll(it)
    displayList.clear()
    displayList.addAll(arrayList)
    var adapter = FavoriteAdapter(displayList)
    rv_movies_list.adapter = adapter
    /* favMovie.orderByChild("userid").equalTo(userid).addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            if (snapshot.exists()) {
                for (moviesSnapshot in snapshot.children) {
                    val mMovie = moviesSnapshot.getValue(FavoriteMovie::class.java)
                    movieArrayList.add(mMovie!!)
                }
                adapter.notifyDataSetChanged()
            }
        }

        override fun onCancelled(error: DatabaseError) {
        }
    }) */
})
mFavoriteViewModel.getMovie()
} */