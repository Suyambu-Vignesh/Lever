package com.circuit.breaker.internal

import com.circuit.breaker.ExperimentalInfo
import com.circuit.breaker.Switch
import com.circuit.breaker.SwitchRefresher
import com.circuit.breaker.internal.cache.Cache
import com.circuit.breaker.internal.data.LeverRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext
import java.util.concurrent.locks.ReentrantLock
import kotlin.coroutines.CoroutineContext

internal class LeverManager(
    private val repository: LeverRepository = LeverRepository()
) : Switch, ExperimentalInfo, SwitchRefresher, CoroutineScope {

    private val cache: Cache by lazy { Cache() }

    private var allExperimentalInfo: List<String> = emptyList()

    override val coroutineContext: CoroutineContext by lazy {
        SupervisorJob() + Dispatchers.Default
    }

    private val reentrantLock: ReentrantLock by lazy { ReentrantLock() }

    override fun <T> getValueFor(key: String, defaultValue: T): T? {
        return cache.getValueFor(key, defaultValue)
    }

    override fun <T> getAsyncValueFor(key: String, defaultValue: T): Flow<T> {
        return cache.getAsyncValueFor(key, defaultValue)
    }

    override fun getAllExperiments(): List<String> {
        return allExperimentalInfo
    }

    override suspend fun refresh() {
        withContext(Dispatchers.IO) {
            repository.getData().collectLatest {
                reentrantLock.lock()

                try {
                    cache.update(it)
                    allExperimentalInfo = it.exp ?: emptyList()
                } finally {
                    reentrantLock.unlock()
                }
            }
        }
    }
}