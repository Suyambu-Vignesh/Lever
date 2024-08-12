package com.circuit.breaker.internal.data.model

internal data class Config(
    val config: HashMap<String, Any>?,
    val exp: ArrayList<String>?
)