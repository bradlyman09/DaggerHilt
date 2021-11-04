package org.android.daggerhilt.mapper

import org.android.daggerhilt.network.response.get_movies.Movie
import org.android.daggerhilt.room.entity.EntityMapper
import org.android.daggerhilt.room.entity.MovieEntity
import javax.inject.Inject

/**
 * Mapper class to convert movie api response to movie entity back and fort
 */
class MovieMapper @Inject constructor(): EntityMapper<MovieEntity, Movie>{
    override fun mapEntityToNetworkModel(entity: MovieEntity): Movie {
        return Movie(
            artistName = entity.trackName,
            collectionPrice = entity.collectionPrice.toDouble(),
            releaseDate = entity.genre,
            artistViewUrl = entity.image,
            description = entity.description
        )
    }

    override fun mapNetworkModelToEntity(networkModel: Movie): MovieEntity {
        return MovieEntity(
            trackName = networkModel.trackName?:"",
            collectionPrice = networkModel.collectionPrice.toString(),
            genre = networkModel.primaryGenreName?:"",
            image = networkModel.artworkUrl100?:"",
            description = networkModel.description?:""
        )
    }

    fun mapMovieNetworkListToEntityList(movieList : List<Movie>) : List<MovieEntity>{
        return movieList.map {
            mapNetworkModelToEntity(it)
        }
    }

    fun mapEntityListToNetworkList(movieList : List<MovieEntity>) : List<Movie>{
        return movieList.map {
            mapEntityToNetworkModel(it)
        }
    }

}