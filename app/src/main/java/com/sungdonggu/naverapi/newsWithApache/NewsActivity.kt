package com.sungdonggu.naverapi.newsWithApache

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sungdonggu.naverapi.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var apiInterface: ApiInterface
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var news: List<News?>? = null
    private var adapter: Adapter? = null
    var progressBar: ProgressBar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        progressBar = findViewById(R.id.progress)
        recyclerView = findViewById(R.id.recycler)
        layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.setLayoutManager(layoutManager)
        fetchNews("")
    }

    fun fetchNews(key: String?) {
        apiInterface = ApiClient.apiClient!!.create(ApiInterface::class.java)
        val call = apiInterface.getNews(key)
        call?.enqueue(object : Callback<List<News?>?> {
            override fun onResponse(call: Call<List<News?>?>, response: Response<List<News?>?>) {
                progressBar!!.visibility = View.GONE
                news = response.body()
                adapter = Adapter(news as List<News>, this@NewsActivity)
                recyclerView!!.adapter = adapter
                adapter!!.notifyDataSetChanged()
                Log.d(TAG,"Response on $news")
            }

            override fun onFailure(call: Call<List<News?>?>, t: Throwable) {
                progressBar!!.visibility = View.GONE
                Toast.makeText(this@NewsActivity, "Error on $t", Toast.LENGTH_LONG).show()
                Log.d(TAG, "Error on $t")
            }
        })
    }

    companion object {
        private const val TAG = "testServer"
    }
}