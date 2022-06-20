package com.anugrah.logintest.model

import io.reactivex.Single

interface NetworksApi {

    fun getNetworks(): Single<List<Networks>>
}