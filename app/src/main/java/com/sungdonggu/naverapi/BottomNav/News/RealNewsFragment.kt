package com.sungdonggu.naverapi.BottomNav.News

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.sungdonggu.naverapi.FirebaseNews.FirebaseNews
import com.sungdonggu.naverapi.FirebaseNews.FirebaseNewsAdapter
import com.sungdonggu.naverapi.R
import kotlinx.android.synthetic.main.fragment_real_news.*
import org.w3c.dom.Text
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class RealNewsFragment : Fragment() {
    private lateinit var fragDatabase: FirebaseDatabase
    private lateinit var fragReference: DatabaseReference
    private lateinit var fragNewsList: ArrayList<FirebaseNews>

    companion object {
        fun newInstance(): RealNewsFragment {
            return RealNewsFragment()
        }

        val TAG = "logTest"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_real_news, container, false)
        val btn_pol: Button = view.findViewById(R.id.frag_btn_politics)
        val btn_eco: Button = view.findViewById(R.id.frag_btn_economics)
        var frag_btn_choosing_date: Button = view.findViewById(R.id.frag_btn_choosing_date)
        var date_here: TextView = view.findViewById(R.id.date_here)

        /**********************************************************CODE will be here**************************************************/
        var today = LocalDate.now().toString().replace("-", "")
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        fragSelectSubject("정치", today)

        date_here.setText("" + year + "/" + month + 1 + "/" + day)
        btn_eco.setOnClickListener {
            fragSelectSubject("경제", today)
            frag_btn_choosing_date.setOnClickListener {
                val dpd = DatePickerDialog(
                    context!!,
                    DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                        var date = "" + mYear + "" + mMonth + 1 + "" + mDay
                        fragSelectSubject("경제", date)
                        Log.d(TAG, "" + mYear + "" + mMonth + 1 + "" + mDay)
                        date_here.setText("" + mYear + "/" + mMonth + 1 + "/" + mDay)
                    },
                    year,
                    month,
                    day
                )
                dpd.show()
            }

        }
        btn_pol.setOnClickListener {
            fragSelectSubject("정치", today)
            frag_btn_choosing_date.setOnClickListener {
                val dpd = DatePickerDialog(
                    context!!,
                    DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                        var date = "" + mYear + "" + mMonth + 1 + "" + mDay
                        fragSelectSubject("정치", date)
                        Log.d(TAG, "" + mYear + "" + mMonth + 1 + "" + mDay)
                        date_here.setText("" + mYear + "/" + mMonth + 1 + "/" + mDay)

                    },
                    year,
                    month,
                    day
                )
                dpd.show()
            }
        }


        /*****************************************************************************************************************************/
        return view
    }

    fun fragSelectSubject(keyword: String, date: String) {
        fragDatabase = FirebaseDatabase.getInstance()


        fragReference = fragDatabase.getReference("News/$date/$keyword")

        if (fragReference != null) {
            fragReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        fragNewsList = ArrayList()
                        for (data in snapshot.children) {
                            var model = data.getValue(FirebaseNews::class.java)
                            fragNewsList.add(model as FirebaseNews)
                        }
                        var fuckingAdapter = FirebaseNewsAdapter(fragNewsList)
                        frag_firebase_recyclerView.adapter = fuckingAdapter
                        frag_firebase_recyclerView.layoutManager = LinearLayoutManager(context)
                        frag_firebase_recyclerView.setHasFixedSize(true)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show()
                }
            })
        }
        if (frag_searchView_news != null) {
            frag_searchView_news.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        fragSearchNews(query)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText != null) {
                        fragSearchNews(newText)
                    }
                    return true
                }
            })
        }
    }

    private fun fragSearchNews(str: String) {
        var fragMyList: ArrayList<FirebaseNews> = ArrayList()
        var fragObj: FirebaseNews
        for (fragObj in fragNewsList) {
            if (fragObj.title?.toLowerCase()?.contains(str.toLowerCase()) == true) {
                fragMyList.add(fragObj)
            }
        }
        val fragAdapterClass: FirebaseNewsAdapter = FirebaseNewsAdapter(fragMyList)
        frag_firebase_recyclerView.adapter = fragAdapterClass
    }

//        val dateList = ArrayList<String>()
//        var today = LocalDate.now() // today
//        val realToday = today.toString().replace("-", "")
//        var startDate = LocalDate.of(2121, 1, 22)
//        while (startDate != today) {
//            var realDate = startDate.toString().replace("-", "")
//            dateList.add(realDate)
//            startDate = startDate.plusDays(1)
//        }
//        dateList.add(today.toString().replace("-", ""))
//
//        for (day in dateList) {
//            fragReference = fragDatabase.getReference("$day/$keyword")
//        }


}