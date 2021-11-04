package org.android.daggerhilt.room.dao

import androidx.room.*
import org.android.daggerhilt.room.entity.MovieEntity

/**
 * DAO for movie entity
 */
@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movieEntity: MovieEntity) : Long

    @Query("SELECT * FROM movies")
    suspend fun getAllMovies() : List<MovieEntity>

    @Query("SELECT * FROM movies WHERE favorite = 1")
    suspend fun getFavoriteMovies() : List<MovieEntity>

    @Update
    suspend fun update(movieEntity: MovieEntity)
}