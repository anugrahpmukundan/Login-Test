package com.anugrah.logintest.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anugrah.logintest.model.Networks
import com.anugrah.logintest.model.NetworksService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel: ViewModel() {

    private var networksService = NetworksService()

    private val disposable = CompositeDisposable()

    val networks = MutableLiveData<List<Networks>>()
    val networksLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        fetchNetworks()
    }

    private fun fetchNetworks(){
        loading.value =true
        disposable.add(
            networksService.getNetworks()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<Networks>>(){
                    override fun onError(e: Throwable?) {
                        networksLoadError.value = true
                        loading.value = false
                    }

                    override fun onSuccess(value: List<Networks>?) {
                        networks.value = value!!
                        networksLoadError.value = false
                        loading.value = false
                    }

                })
        )
    }

}