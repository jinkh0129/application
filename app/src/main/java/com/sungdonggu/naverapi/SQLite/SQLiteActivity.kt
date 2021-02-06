package com.sungdonggu.naverapi.SQLite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.sungdonggu.naverapi.R
import kotlinx.android.synthetic.main.activity_s_q_lite.*

class SQLiteActivity : AppCompatActivity() {
    val DB_NAME = "sqlite_sql"
    val DB_VERSION = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_s_q_lite)

        /**DB헬퍼를 생성-->헬퍼클래스의 onCreate함수 실행-->테이블 형성**/
        val helper = SqliteHelper(this, DB_NAME, DB_VERSION)
        val adpater = SQLRecyclerAdpater()


        val memos = helper.selectMemo()
        adpater.listData.addAll(memos)

        recyclerMemo.adapter = adpater
        recyclerMemo.layoutManager = LinearLayoutManager(this)

        saveButton.setOnClickListener {
            val content = editMemo.text.toString()
            if(content.isNotEmpty()){
                val memo = Memo(null, content, System.currentTimeMillis())
                helper.insertMemo(memo)
            }
        }

    }
}