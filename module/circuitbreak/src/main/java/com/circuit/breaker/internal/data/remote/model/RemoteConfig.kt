package com.circuit.breaker.internal.data.remote.model

import com.google.gson.annotations.SerializedName

internal data class RemoteConfig(
    @SerializedName("config") val config: HashMap<String, Any>?,
    @SerializedName("exp") val exp: ArrayList<String>?
)
