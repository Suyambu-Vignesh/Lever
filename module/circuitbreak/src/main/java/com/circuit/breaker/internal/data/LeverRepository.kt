package com.circuit.breaker.internal.data

import com.circuit.breaker.internal.data.local.LeverLocalDataSource
import com.circuit.breaker.internal.data.model.Config
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest

/**
 * Repository help to get the Data from service
 */
internal class LeverRepository(
    private val remoteRepository: LeverLocalDataSource = LeverLocalDataSource(),
    private val dispatcher: Dispatchers = Dispatchers
) {

    internal suspend fun getData(): Flow<Config> {
        return remoteRepository.getData().mapLatest {
            Config(it.config, it.exp)
        }.catch {
            Config(null, null)
        }.flowOn(
            dispatcher.IO
        )
    }
}
