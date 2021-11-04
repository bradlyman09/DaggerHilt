package org.android.daggerhilt.network.services

import org.android.daggerhilt.network.ResultWrapper
import org.android.daggerhilt.network.response.get_movies.GetMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface MovieRetrofit {
    @GET("search")
    suspend fun get(@QueryMap map : Map<String, String>) : GetMoviesResponse
}