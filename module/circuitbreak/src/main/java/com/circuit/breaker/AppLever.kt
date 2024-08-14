package com.circuit.breaker

import com.circuit.breaker.internal.LeverManager

class AppLever {
    //todo can introduce Config for AuthId, Account Info etc
    companion object {
        private val instance: LeverManager by lazy {
            LeverManager()
        }

        fun getSwitch(): Switch {
            return instance
        }

        fun SwitchRefresher(): SwitchRefresher {
            return instance
        }

        fun ExperimentalInfo(): ExperimentalInfo {
            return instance
        }
    }
}
