package com.sungdonggu.naverapi.RoomKotlin.MakingProfile

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface ProfileDao {
    @Query("SELECT * FROM UserProfile")
    fun getAll(): List<Profile>

    @Insert(onConflict = REPLACE)
    // onConflict = REPLACE는 같은 값의 데이터가 추가될 때 이를 Update로 바꾼다는 의미다.
    fun insert(profile: Profile)

    @Update
    fun update(profile: Profile)

    @Delete
    fun delete(profile: Profile)
}