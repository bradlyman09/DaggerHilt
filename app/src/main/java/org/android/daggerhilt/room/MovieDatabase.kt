package org.android.daggerhilt.room

import androidx.room.Database
import androidx.room.RoomDatabase
import org.android.daggerhilt.room.dao.MovieDao
import org.android.daggerhilt.room.entity.MovieEntity

/**
 * Database details
 */
@Database(entities = [MovieEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase(){

    abstract fun movieDao() : MovieDao

    companion object{
        val DB_NAME = "movie_db"
    }
}