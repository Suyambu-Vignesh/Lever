package com.circuit.breaker.internal.data.local

import com.circuit.breaker.internal.data.remote.model.RemoteConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class LeverLocalDataSource(
    // private val dao: Dao
) {

    internal suspend fun getData(): Flow<RemoteConfig> {
        // return da0.getConfigFlow()
        return flow {

        }
    }
}
