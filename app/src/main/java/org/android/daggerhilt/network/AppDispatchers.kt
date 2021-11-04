package org.android.pmap.network

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Runnable
import kotlin.coroutines.CoroutineContext

interface AppDispatchers {
    fun IO() : CoroutineDispatcher
    fun Main() : CoroutineDispatcher
    fun Default() : CoroutineDispatcher
    fun Unconfined() : CoroutineDispatcher
}