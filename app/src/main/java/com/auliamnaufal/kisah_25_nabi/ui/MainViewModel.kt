package com.auliamnaufal.kisah_25_nabi.ui

import android.text.BoringLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.auliamnaufal.kisah_25_nabi.data.KisahResponse
import com.yoenas.kisah25nabi.data.network.ApiClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel : ViewModel() {

    var isLoading = MutableLiveData<Boolean>()
    var isError = MutableLiveData<Throwable>()
    var kisahResponse = MutableLiveData<List<KisahResponse>>()


    fun getData(responseHandler : (List<KisahResponse>) -> Unit, errorHandler : (Throwable) -> Unit) {
        ApiClient.getApiService().getKisahnabi().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responseHandler(it)
            }, {
                errorHandler(it)
            })
    }

    fun getKisahnabi() {
        isLoading.value = true
        getData({
            isLoading.value = false
            kisahResponse.value = it
        }, {
            isLoading.value = false
            isError.value = it
        })
    }
}