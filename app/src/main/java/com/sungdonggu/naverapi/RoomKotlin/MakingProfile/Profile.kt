package com.sungdonggu.naverapi.RoomKotlin.MakingProfile

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserProfile")
// tableName을 지정해주지 않으면 테이블의 이름은 보통 클래스의 이름으로 간다.
// table 이름을 별도로 지정해주고 싶으면 저렇게 작성하면 된다.
data class Profile(
    @ColumnInfo(name = "name")
    // 칼럼의 이름도 마찬가지로 직접 지정해주고 싶으면 위처럼 사용하면 된다.
    var name: String,
    @ColumnInfo(name = "gender")
    var gender: String,
    @ColumnInfo(name = "email")
    var email: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    override fun toString(): String {
        return "id = $id, name = $name"
    }
}