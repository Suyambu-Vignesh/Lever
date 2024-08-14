package com.circuit.breaker.internal.data.remote

import com.circuit.breaker.internal.data.remote.model.RemoteConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Remote Data Source connects with API work
 */
internal interface RemoteDataSource {
    //todo add retrofit api
    suspend fun getData(): RemoteConfig // Result<RemoteConfig>
}
