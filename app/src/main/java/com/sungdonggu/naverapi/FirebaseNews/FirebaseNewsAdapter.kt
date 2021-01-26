package com.sungdonggu.naverapi.FirebaseNews

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.sungdonggu.naverapi.R
import kotlinx.android.synthetic.main.modified_item_news.view.*

//class FirebaseNewsAdapter(val options: FirebaseRecyclerOptions<FirebaseNews>) :
//    FirebaseRecyclerAdapter<FirebaseNews, FirebaseNewsAdapter.NewsViewHolder>(options) {
//
//    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        var title: TextView
//        var imgSrc: ImageView
//        var pubCom: TextView
//        var pubTime: TextView
//
//        init {
//            title = itemView.findViewById(R.id.modified_title)
//            imgSrc = itemView.findViewById(R.id.modified_image)
//            pubCom = itemView.findViewById(R.id.modified_pubCom)
//            pubTime = itemView.findViewById(R.id.modified_pubTime)
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
//        val view =
//            LayoutInflater.from(parent.context).inflate(R.layout.modified_item_news, parent, false)
//        return NewsViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: NewsViewHolder, position: Int, model: FirebaseNews) {
//        holder.title.setText(model.title)
//        holder.pubCom.setText(model.pubCom)
//        holder.pubTime.setText(model.pubTime)
//        Glide.with(holder.itemView)
//            .load(model.imgSrc)
//            .placeholder(R.drawable.common_google_signin_btn_text_disabled)
//            .error(R.drawable.common_google_signin_btn_text_disabled)
//            .fallback(R.drawable.common_google_signin_btn_text_disabled)
//            .into(holder.imgSrc)
//        holder.itemView.setOnClickListener {
//            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(model.link))
//            it.context.startActivity(intent)
//        }
//    }
//}
class FirebaseNewsAdapter(var newsList: ArrayList<FirebaseNews>) :
    RecyclerView.Adapter<FirebaseNewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title = itemView.modified_title
        var imgSrc = itemView.modified_image
        var pubCom = itemView.modified_pubCom
        var pubTime = itemView.modified_pubTime
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.modified_item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.title.setText(newsList[position].title)
        holder.pubCom.setText(newsList[position].pubCom)
        holder.pubTime.setText(newsList[position].pubTime)
        Glide.with(holder.itemView)
            .load(newsList[position].imgSrc)
            .placeholder(R.drawable.common_google_signin_btn_text_disabled)
            .error(R.drawable.common_google_signin_btn_text_disabled)
            .fallback(R.drawable.common_google_signin_btn_text_disabled)
            .into(holder.imgSrc)
        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(newsList[position].link))
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}