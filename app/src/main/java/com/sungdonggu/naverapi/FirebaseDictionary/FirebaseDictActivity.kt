package com.sungdonggu.naverapi.FirebaseDictionary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.sungdonggu.naverapi.FirebaseNews.FirebaseNewsActivity
import com.sungdonggu.naverapi.R
import com.sungdonggu.naverapi.SearchWithFirebase.SearchDict
import kotlinx.android.synthetic.main.activity_expandable.*
import kotlinx.android.synthetic.main.activity_firebase_dict.*

class FirebaseDictActivity : AppCompatActivity() {
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var dictList: ArrayList<FirebaseDictionary>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase_dict)


        database = FirebaseDatabase.getInstance()
        reference = database.getReference("한국은행 경제용어사전")
    }

    override fun onStart() {
        super.onStart()
        if (reference != null) {
            reference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        dictList = ArrayList<FirebaseDictionary>()
                        for (data in snapshot.children) {
                            var model = data.getValue(FirebaseDictionary::class.java)
                            dictList.add(model as FirebaseDictionary)
                        }
                        var fuckingAdapter = FirebaseDictionaryAdapter(dictList)
                        dict_recyclerView.adapter = fuckingAdapter
                        dict_recyclerView.setHasFixedSize(true)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@FirebaseDictActivity, error.toString(), Toast.LENGTH_LONG)
                        .show()
                }
            })
        }
        if (searchView_dictionary != null) {
            searchView_dictionary.setOnQueryTextListener(object :
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        searchDict(query)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText != null) {
                        searchDict(newText)
                    }
                    return true
                }
            })
        }
    }

    private fun searchDict(str: String) {
        var myList: ArrayList<FirebaseDictionary> = ArrayList<FirebaseDictionary>()
        var obj: FirebaseDictionary
        for (obj in dictList) {
            if (obj.word?.toLowerCase()?.contains(str.toLowerCase()) == true) {
                myList.add(obj)
            }
        }
        val adapterClass: FirebaseDictionaryAdapter = FirebaseDictionaryAdapter(myList)
        dict_recyclerView.adapter = adapterClass
    }
}
