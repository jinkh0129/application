package com.sungdonggu.naverapi.SearchWithFirebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sungdonggu.naverapi.R
import kotlinx.android.synthetic.main.search_card_holder.view.*

class SearchAdapter(var dictList: ArrayList<SearchDict>) :
    RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var word = itemView.search_tv_word
        var def = itemView.search_tv_def

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.search_card_holder, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.word.setText(dictList.get(position).word)
        holder.def.setText(dictList.get(position).def)
    }

    override fun getItemCount(): Int {
        return dictList.size
    }
}