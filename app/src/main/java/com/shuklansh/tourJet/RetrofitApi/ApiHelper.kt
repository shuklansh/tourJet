package com.shuklansh.tourJet.RetrofitApi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiHelper {

    val BASE_URL = "https://api.pexels.com/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

}