package com.sungdonggu.naverapi.DictionaryFirebaseWithRoom

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sungdonggu.naverapi.DictionaryFirebaseWithRoom.RoomDictionary
import com.sungdonggu.naverapi.RoomKotlin.MakingProfile.Profile

@Dao
interface DictionaryDao {
    @Query("SELECT word FROM dictionary")
    fun getWord(): List<String>

    @Query("SELECT def FROM dictionary")
    fun getDef(): List<String>

    @Insert
    fun insert(dictionary: RoomDictionary)
}