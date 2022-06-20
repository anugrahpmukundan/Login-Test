package com.anugrah.logintest.model

import io.reactivex.Single

class NetworksService {

    private val api : NetworksApi
    init {
        api = ""
    }
    fun getNetworks(): Single<List<Networks>> {
        return api.getNetworks()
    }
}