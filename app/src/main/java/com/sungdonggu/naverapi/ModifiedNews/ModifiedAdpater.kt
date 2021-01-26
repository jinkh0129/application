package com.sungdonggu.naverapi.ModifiedNews

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

class ModifiedAdpater(private val modifiedNews: List<ModifiedNews>, private val context: Context) :
    RecyclerView.Adapter<ModifiedAdpater.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var pubCom: TextView
        var image: ImageView
        var pubTime: TextView

        init {
            title = itemView.findViewById(R.id.modified_title)
            pubCom = itemView.findViewById(R.id.modified_pubCom)
            image = itemView.findViewById(R.id.modified_image)
            pubTime = itemView.findViewById(R.id.modified_pubTime)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.modified_item_news, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = modifiedNews[position].title
        holder.pubCom.text = modifiedNews[position].pubCom
        holder.pubTime.text = modifiedNews[position].pubTime
        Glide.with(holder.itemView)
            .load(modifiedNews[position].imgSrc)
            .placeholder(R.drawable.common_google_signin_btn_text_disabled)
            .error(R.drawable.common_google_signin_btn_text_disabled)
            .fallback(R.drawable.common_google_signin_btn_text_disabled)
            .into(holder.image)
        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(modifiedNews[position].link))
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return modifiedNews.size
    }
}