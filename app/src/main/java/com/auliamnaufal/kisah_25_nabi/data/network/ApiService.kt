package com.auliamnaufal.kisah_25_nabi.data.network

import com.auliamnaufal.kisah_25_nabi.data.KisahResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET

interface ApiService {

    @GET("kisahnabi")
    fun getKisahnabi() : Flowable<List<KisahResponse>>
}