package com.sungdonggu.naverapi.OfflineDictionary

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.sungdonggu.naverapi.R
import kotlinx.android.synthetic.main.offline_dictionary_item.view.*

class SQLAdapter() : RecyclerView.Adapter<SQLAdapter.MySqlViewHolder>() {
    companion object {
        private const val TAG = "LOGTEST"
    }

    var dataList = ArrayList<sqlDictionary>()

    class MySqlViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var offlineLinearLayout = itemView.offline_dict_expandable_linearlayout
        var offlineExpandable_layout = itemView.offline_dict_expandable_layout
        var btn_like = itemView.btn_like
        fun initializeDict(dict: sqlDictionary) {
            itemView.offline_tv_word.text = "${dict.word}"
            itemView.offline_tv_def.text = "${dict.def}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MySqlViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.offline_dictionary_item, parent, false)
        return MySqlViewHolder(view)
    }

    override fun onBindViewHolder(holder: MySqlViewHolder, position: Int) {
        val dict = dataList.get(position)
        holder.initializeDict(dict)

        holder.offlineExpandable_layout.visibility =
            if (dataList.get(position).expandable) View.VISIBLE else View.GONE
        holder.offlineLinearLayout.setOnClickListener {
            val dicts = dataList.get(position)
            dicts.expandable = !dicts.expandable
            notifyItemChanged(position)
        }

    }
    override fun getItemCount(): Int {
        return dataList.size
    }
}