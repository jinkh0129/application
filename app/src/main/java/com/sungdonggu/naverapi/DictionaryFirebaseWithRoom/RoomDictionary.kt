package com.sungdonggu.naverapi.DictionaryFirebaseWithRoom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dictionary")
data class RoomDictionary(
    @ColumnInfo(name = "word")
    var word: String,
    @ColumnInfo(name = "def")
    var def: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}