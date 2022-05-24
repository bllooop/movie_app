package com.example.moviecatalog.localdata

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName="favorite_movie")
data class FavoriteMovie (
    var id_movie : String,
    var title : String,
    var poster_path : String,
    var release_date : String
        ):Serializable{
            @PrimaryKey(autoGenerate = true)
            var id : Int = 0

}