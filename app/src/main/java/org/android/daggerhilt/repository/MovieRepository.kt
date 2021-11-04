package org.android.daggerhilt.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.android.daggerhilt.mapper.MovieMapper
import org.android.daggerhilt.network.ResultWrapper
import org.android.daggerhilt.network.response.get_movies.GetMoviesResponse
import org.android.daggerhilt.network.services.MovieRetrofit
import org.android.daggerhilt.network.AppDispatchersImpl
import org.android.daggerhilt.room.dao.MovieDao
import org.android.daggerhilt.room.entity.MovieEntity
import javax.inject.Inject

class MovieRepository
    @Inject constructor(
        val movieRetrofit: MovieRetrofit,
        val movieDao: MovieDao,
        val movieMapper: MovieMapper,
        val appDispatchersImpl: AppDispatchersImpl
    ){

    /**
     * get movie list from api
     */
    suspend fun getMoviesFromApi(queryMap : Map<String, String>) : Flow<ResultWrapper<GetMoviesResponse>> {
        return flow {
            emit(ResultWrapper.ApiLoading(true))
            emit(ResultWrapper.ApiSuccess(movieRetrofit.get(queryMap)))
            emit(ResultWrapper.ApiLoading(false))
        }
    }

    /**
     * get movie list from database
     */
    suspend fun getAllMoviesFromDatabase() : List<MovieEntity>{
        return movieDao.getAllMovies()
    }

    /**
     * get favorite movie list from database
     */
    suspend fun getFavoriteMoviesFromDatabase() : List<MovieEntity>{
        return movieDao.getFavoriteMovies()
    }

    /**
     * add movie data in database
     */
    suspend fun addMovieToDatabase(movieEntity: MovieEntity) : Long{
        return movieDao.insert(movieEntity)
    }

    /**
     * add list of movies in database
     */
    suspend fun addMovieListToDatabsae(movieEntity: List<MovieEntity>){
        movieEntity.forEach {
            movieDao.insert(it)
        }
    }

    /**
     * update a movie data in database
     */
    suspend fun updateMovie(movieEntity: MovieEntity){
        movieDao.update(movieEntity)
    }
}