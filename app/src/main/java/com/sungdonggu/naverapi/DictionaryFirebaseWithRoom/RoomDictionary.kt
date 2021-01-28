package com.sungdonggu.naverapi.DictionaryFirebaseWithRoom

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "dictionary")
data class RoomDictionary(
    @ColumnInfo(name = "word")
    var word: String,
    @ColumnInfo(name = "def")
    var def: String
) {
}