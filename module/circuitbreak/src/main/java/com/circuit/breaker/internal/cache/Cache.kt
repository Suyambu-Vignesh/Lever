@file:Suppress("UNCHECKED_CAST")

package com.circuit.breaker.internal.cache

import android.os.Parcelable
import com.circuit.breaker.internal.data.model.Config
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

/**
 * Internal cache which holds the Config Map and The StateFlow
 */
internal class Cache {
    private val configMap: HashMap<String, Any> by lazy { HashMap<String, Any>() }
    private val asyncValueMap by lazy { HashMap<String, FlowInfo<*>>() }

    fun getValueFor(key: String, defaultValue: Parcelable?): Parcelable? {
        val record = configMap[key]

        return record as? Parcelable ?: defaultValue
    }

    fun <T> getValueFor(key: String, defaultValue: T?): T? {
        val value = configMap[key] ?: return defaultValue

        return value as? T ?: defaultValue
    }

    fun <T> getAsyncValueFor(key: String, defaultValue: T): Flow<T> {
        val value = getValueFor(key, defaultValue)
        val flow = asyncValueMap[key]
        return if (flow != null) {
            flow.stateFlow as Flow<T>
        } else {
            val newSharedFlow = MutableSharedFlow<T>()
            asyncValueMap[key] = FlowInfo(newSharedFlow, defaultValue)
            newSharedFlow
        }
    }

    fun update(config: Config) {
        configMap.clear()
        config.config?.let {
            configMap.putAll(it)
        }

        asyncValueMap.forEach {
            val defaultValue = it.value.defaultValue
            val value = getValueFor(it.key, it.value.defaultValue) ?: it.value.defaultValue
            it.value.stateFlow.tryEmit(
                value
            )
        }
    }
}

private data class FlowInfo<T>(
    val stateFlow: MutableSharedFlow<T>,
    val defaultValue: T
)
