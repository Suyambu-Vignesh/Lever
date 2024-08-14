package com.circuit.breaker

interface SwitchRefresher {
    suspend fun refresh()
}
