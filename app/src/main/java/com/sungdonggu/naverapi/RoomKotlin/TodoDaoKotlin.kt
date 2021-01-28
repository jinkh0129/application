package com.sungdonggu.naverapi.RoomKotlin

import androidx.room.*

// 어떤 동작을 제어할지 정해주는 인터페이스
@Dao
interface TodoDaoKotlin {
    @Query("SELECT * FROM TodoKotlin")
    fun getAll(): List<TodoKotlin>

    @Insert
    fun insert(todo: TodoKotlin)

    @Update
    fun update(todo: TodoKotlin)

    @Delete
    fun delete(todo: TodoKotlin)
}