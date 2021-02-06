package com.sungdonggu.naverapi.SQLite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sungdonggu.naverapi.R
import kotlinx.android.synthetic.main.sqlite_item.view.*

class SQLRecyclerAdpater : RecyclerView.Adapter<SQLRecyclerAdpater.MyViewHolder>() {
    var listData = mutableListOf<Memo>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setMemo(memo: Memo) {
            itemView.textNo.text = "${memo.no}"
            itemView.textContent.text = "${memo.content}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sqlite_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val memo = listData.get(position)
        holder.setMemo(memo)
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}