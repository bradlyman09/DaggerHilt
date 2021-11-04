package org.android.daggerhilt.network

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import org.android.daggerhilt.network.response.errorResponse.ErrorResponse
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * Result wrapper to distinguish api response state
 */
sealed class ResultWrapper<out T> {
    data class ApiLoading<T>(val isLoading : Boolean) : ResultWrapper<T>()
    data class ApiSuccess<out T>(val body: T): ResultWrapper<T>()
    data class ApiError(val code : Int? = null, val error : ErrorResponse? = null): ResultWrapper<Nothing>()
}