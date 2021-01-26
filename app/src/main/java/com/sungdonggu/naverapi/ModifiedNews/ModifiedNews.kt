package com.sungdonggu.naverapi.ModifiedNews

import com.google.gson.annotations.SerializedName

class ModifiedNews {
    @SerializedName("id")
    val id: Int = 0

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

    @SerializedName("sort")
    val sort: String? = null
}