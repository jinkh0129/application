package com.sungdonggu.naverapi.OfflineDictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.sungdonggu.naverapi.R
import kotlinx.android.synthetic.main.activity_offline_dictionary.*

class OfflineDictionaryActivity : AppCompatActivity() {

    private lateinit var offlineDatabase: FirebaseDatabase
    private lateinit var offlineReference: DatabaseReference
    var listDict: MutableList<sqlDictionary> = mutableListOf()

    val DB_NAME = "dictionary"
    val DB_VERSION = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offline_dictionary)

        val helper = HELPER(this, DB_NAME, DB_VERSION)
        val adapter = SQLAdapter()

        offlineDatabase = FirebaseDatabase.getInstance()
        offlineReference = offlineDatabase.getReference("한국은행 경제용어사전")
        if (offlineReference != null) {
            offlineReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (data in snapshot.children) {
                            val dict = sqlDictionary(
                                null,
                                data.child("word").value.toString(),
                                data.child("content").value.toString()
                            )
                            helper.insertDict(dict)
                        }

                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(
                        this@OfflineDictionaryActivity,
                        error.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
        }
        val dicts = helper.selectDict()
        adapter.dataList.addAll(dicts)
        offline_recyclerView.adapter = adapter
        offline_recyclerView.layoutManager =
            LinearLayoutManager(this@OfflineDictionaryActivity)
        offline_recyclerView.setHasFixedSize(true)


    }
}