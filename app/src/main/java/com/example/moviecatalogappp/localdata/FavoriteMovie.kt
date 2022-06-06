package com.example.moviecatalog.localdata


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FavoriteMovie (
    var id_movie : String = "",
    var title : String= "",
    var poster_path : String = "",
    var release_date : String= ""
    )
