package com.circuit.breaker.internal.data

import com.circuit.breaker.internal.data.remote.RemoteDataSource

internal class RefreshManager(
    private val dataSource: RemoteDataSource,
    //private val dao: DAO
) {

    internal suspend fun getData() {
        dataSource.getData()?.let {
            //dao.addRecoreds()
        }
    }
}
