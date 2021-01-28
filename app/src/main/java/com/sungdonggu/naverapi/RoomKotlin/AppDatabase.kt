package com.sungdonggu.naverapi.RoomKotlin

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TodoKotlin::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDaoKotlin(): TodoDaoKotlin
}