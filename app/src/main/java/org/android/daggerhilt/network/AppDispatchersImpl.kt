package org.android.daggerhilt.network

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.android.pmap.network.AppDispatchers
import javax.inject.Inject

class AppDispatchersImpl @Inject constructor() : AppDispatchers {
    override fun IO(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    override fun Main(): CoroutineDispatcher {
        return Dispatchers.Main
    }

    override fun Default(): CoroutineDispatcher {
        return Dispatchers.Default
    }

    override fun Unconfined(): CoroutineDispatcher {
        return Dispatchers.Unconfined
    }
}