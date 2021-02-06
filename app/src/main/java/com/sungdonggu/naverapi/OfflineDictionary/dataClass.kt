package com.sungdonggu.naverapi.OfflineDictionary

data class sqlDictionary(
    var id: Long?,
    var word: String,
    var def: String,
    var expandable: Boolean = false
)