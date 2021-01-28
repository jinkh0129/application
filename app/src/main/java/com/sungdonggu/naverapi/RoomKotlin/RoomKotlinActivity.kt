package com.sungdonggu.naverapi.RoomKotlin

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.sungdonggu.naverapi.R
import kotlinx.android.synthetic.main.activity_room_kotlin.*

class RoomKotlinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_kotlin)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "TodoKotlin"
        ).allowMainThreadQueries()
            .build()

        kot_result_text.text = db.todoDaoKotlin().getAll().toString()

        kot_add_button.setOnClickListener {
            db.todoDaoKotlin().insert(TodoKotlin(kot_todo_edit.text.toString()))
            kot_result_text.text = db.todoDaoKotlin().getAll().toString()
        }
    }

}