package com.sungdonggu.naverapi.newsWithApache

import com.google.gson.annotations.SerializedName

class News {
    @SerializedName("id")
    val id = 0

    @SerializedName("title")
    val title: String? = null

    @SerializedName("link")
    val link: String? = null

    @SerializedName("imgSrc")
    val imgSrc: String? = null

    @SerializedName("pubCom")
    val pubCom: String? = null

    @SerializedName("pubTime")
    val pubTime: String? = null

    @SerializedName("Sort")
    val sort: String? = null
}