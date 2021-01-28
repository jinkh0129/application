package com.sungdonggu.naverapi.RoomKotlin

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoKotlin(
    var title: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

// id는 PR이긴 하지만 입력하지 않아도 되니까 생성자에서 제외한다.
// 그래서 입력을 해야하는 title만 생성자로 넣어주고
// id값은 {}(중괄호)안으로 넣어준다. 단, 이때 initialize를 해줘야 하는데 그냥 0으로 해준다.