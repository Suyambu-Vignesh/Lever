package com.circuit.breaker.internal.data.remote

import com.circuit.breaker.internal.data.remote.model.RemoteConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Remote Data Source connects with API work
 */
internal class RemoteDataSource {
    //todo add retrofit api
    internal suspend fun getData(): Flow<RemoteConfig> {
        return flow {

        }
    }
}