package com.aneke.peter.pbmob.network

import com.aneke.peter.pbmob.network.model.SearchImageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("api/")
    suspend fun fetchHits(@Query("key") key : String, @Query("q") query : String, @Query("per_page") size : Int = 100) : SearchImageResponse

}