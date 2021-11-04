package org.android.daggerhilt.ui.main

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.android.daggerhilt.network.ResultWrapper
import org.android.daggerhilt.network.response.get_movies.GetMoviesResponse
import org.android.daggerhilt.network.response.get_movies.Movie
import org.android.daggerhilt.repository.MovieRepository
import org.android.daggerhilt.room.entity.MovieEntity
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val movieRepository: MovieRepository
): ViewModel(){

    private val _getMoviesResponse = MutableLiveData<ResultWrapper<GetMoviesResponse>>()
    val getMoviesResponse : LiveData<ResultWrapper<GetMoviesResponse>> = _getMoviesResponse

    private val _isRefreshList = MutableLiveData<Boolean>()
    val isRefreshList : LiveData<Boolean> = _isRefreshList

    val movieList = mutableListOf<MovieEntity>()


    /**
     * get movies from api and update our local database
     */
    fun getMovies(term : String ="star", country : String = "au"){
        viewModelScope.launch{
            val queryParams = mutableMapOf<String, String>()
            queryParams["term"] = term
            queryParams["country"] = country

            movieRepository.getMoviesFromApi(queryParams)
                .onEach {
                    _getMoviesResponse.value = it

                    if (it is ResultWrapper.ApiSuccess){
                        movieRepository.addMovieListToDatabsae(mapMovieToMovieEntity(it.body.movieList))
                    }
                }
                .launchIn(viewModelScope)
        }
    }

    fun mapMovieToMovieEntity(movieList : List<Movie>): List<MovieEntity>{
        return movieRepository.movieMapper.mapMovieNetworkListToEntityList(movieList)
    }

    fun getAllMoviesFromDatabase(){
        viewModelScope.launch {
            movieList.clear()
            movieList.addAll(movieRepository.getAllMoviesFromDatabase())
            _isRefreshList.value = movieList.size > 0 //trigger refresh list if movielist size is not empty
        }

    }

    fun getFavoriteMoviesFromDatabase(){
        viewModelScope.launch {
            movieList.clear()
            movieList.addAll(movieRepository.getFavoriteMoviesFromDatabase())
            _isRefreshList.value = movieList.size > 0 //trigger refresh list if movielist size is not empty
        }
    }

    fun updateMovie(movieEntity: MovieEntity){
        viewModelScope.launch {
            movieRepository.updateMovie(movieEntity)
        }
    }
}