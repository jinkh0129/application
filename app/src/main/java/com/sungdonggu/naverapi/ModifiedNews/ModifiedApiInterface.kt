package com.sungdonggu.naverapi.ModifiedNews

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ModifiedApiInterface {
    @GET("getNews.php")
    fun modifiedGetNews(@Query("key") keyword: String?): retrofit2.Call<List<ModifiedNews?>?>
}