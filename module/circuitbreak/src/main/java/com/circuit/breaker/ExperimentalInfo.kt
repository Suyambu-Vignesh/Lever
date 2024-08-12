package com.circuit.breaker

interface ExperimentalInfo {

    /**
     * Helper fun that get all the experiment ids/tags
     */
    fun getAllExperiments(): List<String>
}