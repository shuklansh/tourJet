package com.shuklansh.tourJet.RetrofitApi

import com.shuklansh.tourJet.Model.pexelResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface InterfaceApi {

    @Headers("Authorization: APIKEY")
    @GET("v1/search")
    suspend fun getImages(
        @Query("query") query: String,
        @Query("per_page") perpage: Int
    ): pexelResponse

}