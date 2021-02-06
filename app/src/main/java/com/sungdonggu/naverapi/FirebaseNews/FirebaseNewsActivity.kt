package com.sungdonggu.naverapi.FirebaseNews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.facebook.drawee.gestures.GestureDetector
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.sungdonggu.naverapi.FirebaseDictionary.FirebaseDictActivity
import com.sungdonggu.naverapi.R
import kotlinx.android.synthetic.main.activity_firebase_dict.*
import kotlinx.android.synthetic.main.activity_firebase_news.*

class FirebaseNewsActivity : AppCompatActivity() {
    /**기존방법**/
//    var firebase_Adapter: FirebaseNewsAdapter? = null
//    var firebase_BaseNews: DatabaseReference? = null
    /**********/

    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var newsList: ArrayList<FirebaseNews>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firebase_news)

        btn_economics.setOnClickListener {
            selectSubject("경제")
        }

        btn_politics.setOnClickListener {
            selectSubject("정치")
        }

        btn_to_dictionary.setOnClickListener {
            val intent = Intent(this@FirebaseNewsActivity, FirebaseDictActivity::class.java)
            startActivity(intent)
        }


//        database = FirebaseDatabase.getInstance()
//        reference = database.getReference("20210122/경제")
//
//        if (reference != null) {
//            reference.addValueEventListener(object : ValueEventListener {
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    if (snapshot.exists()) {
//                        newsList = ArrayList<FirebaseNews>()
//                        for (data in snapshot.children) {
//                            var model = data.getValue(FirebaseNews::class.java)
//                            newsList.add(model as FirebaseNews)
//                        }
//                        var damnAdapter = FirebaseNewsAdapter(newsList)
//                        firebase_recyclerView.adapter = damnAdapter
//                        firebase_recyclerView.layoutManager =
//                            LinearLayoutManager(this@FirebaseNewsActivity)
//                        firebase_recyclerView.setHasFixedSize(true)
//                    }
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                    Toast.makeText(this@FirebaseNewsActivity, error.toString(), Toast.LENGTH_LONG)
//                        .show()
//                }
//            })
//        }
//        if (searchView_news != null) {
//            searchView_news.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//                override fun onQueryTextSubmit(query: String?): Boolean {
//                    if (query != null) {
//                        searchNews(query)
//                    }
//                    return true
//                }
//
//                override fun onQueryTextChange(newText: String?): Boolean {
//                    if (newText != null) {
//                        searchNews(newText)
//                    }
//                    return true
//                }
//            })
//        }


        /**기존방법**/
//        firebase_BaseNews = FirebaseDatabase.getInstance().getReference("20210122/경제")
//        firebase_recyclerView.setHasFixedSize(true)
//        firebase_recyclerView.layoutManager = LinearLayoutManager(this)
//
//        val options_news = FirebaseRecyclerOptions.Builder<FirebaseNews>()
//            .setQuery(firebase_BaseNews!!, FirebaseNews::class.java).build()
//
//        firebase_Adapter = FirebaseNewsAdapter(options_news)
//        firebase_recyclerView.adapter = firebase_Adapter
//        firebase_Adapter!!.startListening()
        /**********/
    }

    fun selectSubject(keyword: String) {
        database = FirebaseDatabase.getInstance()
        reference = database.getReference("20210122/$keyword")

        if (reference != null) {
            reference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        newsList = ArrayList()
                        for (data in snapshot.children) {
                            var model = data.getValue(FirebaseNews::class.java)
                            newsList.add(model as FirebaseNews)
                        }
                        var damnAdapter = FirebaseNewsAdapter(newsList)
                        firebase_recyclerView.adapter = damnAdapter
                        firebase_recyclerView.layoutManager =
                            LinearLayoutManager(this@FirebaseNewsActivity)
                        firebase_recyclerView.setHasFixedSize(true)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@FirebaseNewsActivity, error.toString(), Toast.LENGTH_LONG)
                        .show()
                }
            })
        }



        if (searchView_news != null) {
            searchView_news.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        searchNews(query)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText != null) {
                        searchNews(newText)
                    }
                    return true
                }
            })
        }
    }

    private fun searchNews(str: String) {
        var myList: ArrayList<FirebaseNews> = ArrayList<FirebaseNews>()
        var obj: FirebaseNews
        for (obj in newsList) {
            if (obj.title?.toLowerCase()?.contains(str.toLowerCase()) == true) {
                myList.add(obj)
            }
        }
        val adapterClass: FirebaseNewsAdapter = FirebaseNewsAdapter(myList)
        firebase_recyclerView.adapter = adapterClass
    }

//    override fun onStart() {
//        super.onStart()
//        firebase_Adapter!!.startListening()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        firebase_Adapter!!.stopListening()
//    }
}