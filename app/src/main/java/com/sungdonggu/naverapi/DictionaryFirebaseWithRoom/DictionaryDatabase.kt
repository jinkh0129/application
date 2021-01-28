package com.sungdonggu.naverapi.DictionaryFirebaseWithRoom

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RoomDictionary::class], version = 1)
abstract class DictionaryDatabase : RoomDatabase() {
    abstract fun dictionaryDao(): DictionaryDao
}