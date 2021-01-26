package com.sungdonggu.naverapi.BottomNav.Dict

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.sungdonggu.naverapi.FirebaseDictionary.FirebaseDictionary
import com.sungdonggu.naverapi.FirebaseDictionary.FirebaseDictionaryAdapter
import com.sungdonggu.naverapi.R
import kotlinx.android.synthetic.main.fragment_real_dict.*

class RealDictFragment : Fragment() {
    private lateinit var fragDatabase: FirebaseDatabase
    private lateinit var fragReference: DatabaseReference
    private lateinit var fragDictList: ArrayList<RealDictionary>
    private lateinit var frag_dict_recyclerView: RecyclerView

    companion object {
        fun newInstance(): RealDictFragment {
            return RealDictFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_real_dict, container, false)
        /**********************************************************CODE will be here**************************************************/
        fragDatabase = FirebaseDatabase.getInstance()
        fragReference = fragDatabase.getReference("한국은행 경제용어사전")
        frag_dict_recyclerView = view.findViewById(R.id.frag_dict_recyclerView)
        startFragment()

        /*****************************************************************************************************************************/
        return view
    }

    fun startFragment() {
        if (fragReference != null) {
            fragReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        fragDictList = ArrayList()
                        for (data in snapshot.children) {
                            var model = data.getValue(RealDictionary::class.java)
                            fragDictList.add(model as RealDictionary)
                        }
                        var fuckingAdapter = RealDictAdapter(fragDictList)
                        frag_dict_recyclerView.adapter = fuckingAdapter
                        frag_dict_recyclerView.setHasFixedSize(true)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show()
                }
            })
        }
        if (frag_searchView_dictionary != null) {
            frag_searchView_dictionary.setOnQueryTextListener(object :
                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        fragSearchDict(query)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText != null) {
                        fragSearchDict(newText)
                    }
                    return true
                }
            })
        }
    }

    private fun fragSearchDict(str: String) {
        var fragMyList: ArrayList<RealDictionary> = ArrayList<RealDictionary>()
        var obj: RealDictionary
        for (obj in fragDictList) {
            if (obj.word?.toLowerCase()?.contains(str.toLowerCase()) == true) {
                fragMyList.add(obj)
            }
        }
        val fragAdapterClass = RealDictAdapter(fragDictList)
        frag_dict_recyclerView.adapter = fragAdapterClass
    }
}