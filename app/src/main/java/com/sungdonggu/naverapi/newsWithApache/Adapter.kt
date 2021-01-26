package com.sungdonggu.naverapi.newsWithApache

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sungdonggu.naverapi.R

class Adapter(private val news: List<News>, private val context: Context) :
    RecyclerView.Adapter<Adapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: Adapter.MyViewHolder, position: Int) {
        holder.title.text = news[position].title
        holder.publisher.text = news[position].pubCom
        holder.time.text = news[position].pubTime
        Glide.with(holder.itemView)
            .load(news[position].imgSrc)
            .placeholder(R.drawable.common_google_signin_btn_icon_dark_focused)
            .error(R.drawable.common_google_signin_btn_icon_dark_focused)
            .fallback(R.drawable.common_google_signin_btn_icon_dark_focused)
            .into(holder.image)
        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(news[position].link))
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return news.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var publisher: TextView
        var image: ImageView
        var time: TextView

        init {
            title = itemView.findViewById(R.id.title)
            publisher = itemView.findViewById(R.id.pubCom)
            image = itemView.findViewById(R.id.iv_thumb)
            time = itemView.findViewById(R.id.pubTime)
        }
    }
}