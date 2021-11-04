package org.android.daggerhilt.network.response.get_movies

import com.google.gson.annotations.SerializedName

data class GetMoviesResponse(
    @SerializedName("resultCount")
    val resultCount : Int,
    @SerializedName("results")
    val movieList : List<Movie>
)