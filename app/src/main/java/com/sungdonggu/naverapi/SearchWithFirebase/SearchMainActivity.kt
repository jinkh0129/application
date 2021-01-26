package com.sungdonggu.naverapi.SearchWithFirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import com.google.firebase.database.*
import com.sungdonggu.naverapi.FirebaseDictionary.FirebaseDictionary
import com.sungdonggu.naverapi.R
import kotlinx.android.synthetic.main.activity_search_main.*

class SearchMainActivity : AppCompatActivity() {
    private lateinit var ref: DatabaseReference
    private lateinit var dictList: ArrayList<SearchDict>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_main)

        ref = FirebaseDatabase.getInstance().getReference().child("Dictionary")


    }

    override fun onStart() {
        super.onStart()
        if (ref != null) {
            ref.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        dictList = ArrayList<SearchDict>()
                        for (data in snapshot.children) {
                            var model = data.getValue(SearchDict::class.java)
                            dictList.add(model as SearchDict)
                        }
                        var fuckingAdapter = SearchAdapter(dictList)
                        rv_search.adapter = fuckingAdapter
                        rv_search.setHasFixedSize(true)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@SearchMainActivity, error.toString(), Toast.LENGTH_LONG)
                        .show()
                }
            })
        }

        if (searchView != null) {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        search(query)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText != null) {
                        search(newText)
                    }
                    return true
                }
            })
        }
    }

    private fun search(str: String) {
        var myList: ArrayList<SearchDict> = ArrayList<SearchDict>()
        var obj: SearchDict
        for (obj in dictList) {
            if (obj.word?.toLowerCase()?.contains(str.toLowerCase()) == true) {
                myList.add(obj)
            }
        }
        val adapterClass: SearchAdapter = SearchAdapter(myList)
        rv_search.adapter = adapterClass
    }
}