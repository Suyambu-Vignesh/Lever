package com.circuit.breaker

import android.os.Parcelable
import kotlinx.coroutines.flow.Flow

interface Switch {
    fun <T> getValueFor(key: String, defaultValue: T): T?

    fun <T> getAsyncValueFor(key: String, defaultValue: T): Flow<T>
}
