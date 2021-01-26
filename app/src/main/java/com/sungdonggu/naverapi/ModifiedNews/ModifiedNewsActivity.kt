package com.sungdonggu.naverapi.ModifiedNews

import android.app.ProgressDialog
import android.app.ProgressDialog.show
import android.app.SearchManager
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sungdonggu.naverapi.R
import kotlinx.android.synthetic.main.activity_news.*
import okhttp3.Callback
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.lang.Exception

class ModifiedNewsActivity : AppCompatActivity() {
    lateinit var modifiedRecyclerView: RecyclerView
    lateinit var modifiedApiInterface: ModifiedApiInterface
    private var modifiedLayoutManager: RecyclerView.LayoutManager? = null
    private var modifiedNews: List<ModifiedNews?>? = null
    private var modifiedAdapter: ModifiedAdpater? = null
    var modifiedProgressBar: ProgressBar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modified_news)

        modifiedProgressBar = findViewById(R.id.modified_progress)
        modifiedRecyclerView = findViewById(R.id.modified_recyclerView)
        modifiedLayoutManager = LinearLayoutManager(this)
        modifiedRecyclerView.setHasFixedSize(true)
        modifiedRecyclerView.layoutManager = modifiedLayoutManager

    }

    fun modifiedFetchNews(key: String?) {
        modifiedApiInterface =
            ModifiedApiClient.apiClient!!.create(ModifiedApiInterface::class.java)
        val call = modifiedApiInterface.modifiedGetNews(key)
        call?.enqueue(object : retrofit2.Callback<List<ModifiedNews?>?> {
            override fun onResponse(
                call: Call<List<ModifiedNews?>?>,
                response: Response<List<ModifiedNews?>?>
            ) {
                modifiedProgressBar!!.visibility = View.GONE
                modifiedNews = response.body()
                modifiedAdapter =
                    ModifiedAdpater(modifiedNews as List<ModifiedNews>, this@ModifiedNewsActivity)
                modifiedRecyclerView!!.adapter = modifiedAdapter
                modifiedAdapter!!.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<ModifiedNews?>?>, t: Throwable) {
                modifiedProgressBar!!.visibility = View.GONE
                Toast.makeText(this@ModifiedNewsActivity, "$t", Toast.LENGTH_LONG).show()
            }
        })
    }

//    private inner class modifiedFetchNews : AsyncTask<String?, Void?, String?>() {
//        lateinit var modifiedRecyclerView: RecyclerView
//        lateinit var modifiedApiInterface: ModifiedApiInterface
//        private var modifiedLayoutManager: RecyclerView.LayoutManager? = null
//        private var modifiedNews: List<ModifiedNews?>? = null
//        private var modifiedAdapter: ModifiedAdpater? = null
//        var modifiedProgressDialog: ProgressDialog? = null
//        var errorString: String? = null
//
//        override fun onPreExecute() {
//            super.onPreExecute()
//            modifiedProgressDialog = ProgressDialog.show(
//                this@ModifiedNewsActivity, "Please Wait", null, true, true
//            )
//        }
//
//        override fun doInBackground(vararg params: String?): String? {
//            var keyword = params[0]
//            try {
//                modifiedApiInterface = ModifiedApiInterface!!.create
//            } catch (e: Exception) {
//                Log.d(TAG, "InsertData : Error ", e)
//                errorString = e.toString()
//            }
//
//        }
//
//
//        override fun onPostExecute(result: String?) {
//            super.onPostExecute(result)
//            modifiedProgressDialog?.dismiss()
//            Log.d(TAG, "response : $result")
//
//
//            modifiedRecyclerView = findViewById(R.id.modified_recyclerView)
//            modifiedLayoutManager = LinearLayoutManager(this@ModifiedNewsActivity)
//            modifiedRecyclerView.setHasFixedSize(true)
//            modifiedRecyclerView.layoutManager = modifiedLayoutManager
//        }
//
//
//    }

    /**JSON파싱해오는 작업을 비동기로 가져와야하는데 어디다가 넣어줄까
     * 기존의 fetchNews메서드가 가져오는 작업이니까 비동기로 처리하면 될 것 같은데
     * */


    companion object {
        private const val TAG = "Modified TEST"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        val searchView = menu!!.findItem(R.id.search)!!.actionView as androidx.appcompat.widget.SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))

        searchView.setIconifiedByDefault(false)

        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                modifiedFetchNews(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                modifiedFetchNews(newText)
                return false
            }
        })
        return true
    }
}