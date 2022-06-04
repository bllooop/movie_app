package com.example.moviecatalog.localdata

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviecatalogapp.models.Movie

@Database(
    entities = [FavoriteMovie::class],
    version = 1
)


abstract class FavoriteMovieDatabase : RoomDatabase(){
    abstract fun getFavoriteMovieDao(): FavoriteMovieDAO
    companion object{
        private var INSTANCE: FavoriteMovieDatabase?=null

        fun getAppDatabase(context: Context) : FavoriteMovieDatabase?{
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder<FavoriteMovieDatabase>(
                    context, FavoriteMovieDatabase::class.java,  "AppDB"
                )
                    .allowMainThreadQueries()
                    .build()

            }
            return INSTANCE
        }

    }
}