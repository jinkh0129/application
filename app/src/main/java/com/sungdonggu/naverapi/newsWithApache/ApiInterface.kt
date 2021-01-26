package com.sungdonggu.naverapi.newsWithApache

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("getNews.php")
    fun getNews(@Query("key") keyword: String?): Call<List<News?>?>
}