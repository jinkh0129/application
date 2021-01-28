package com.sungdonggu.naverapi.RoomKotlin.MakingProfile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.sungdonggu.naverapi.R
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val db = Room.databaseBuilder(
            applicationContext, ProfileDatabase::class.java, "UserProfile"
        ).allowMainThreadQueries()
            .build()


        tv_result_room.text = db.profileDao().getAll().toString()


        btn_insert_room.setOnClickListener {
            db.profileDao().insert(
                Profile(
                    et_name_room.text.toString(),
                    et_gender_room.text.toString(),
                    et_email_room.text.toString()
                )
            )
            tv_result_room.text = db.profileDao().getAll().toString()
        }
    }
}