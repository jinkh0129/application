package com.sungdonggu.naverapi.FirebaseDictionary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sungdonggu.naverapi.R
import kotlinx.android.synthetic.main.item_dict.view.*

class FirebaseDictionaryAdapter(var dictList: ArrayList<FirebaseDictionary>) :
    RecyclerView.Adapter<FirebaseDictionaryAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var word = itemView.tv_word
        var content = itemView.tv_def
        var linearLayout = itemView.dict_expandable_linearlayout
        var expandable_layout = itemView.dict_expandable_layout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_dict, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.word.text = dictList[position].word
        holder.content.text = dictList[position].content

        holder.expandable_layout.visibility = if(dictList[position].expandable) View.VISIBLE else View.GONE
        holder.linearLayout.setOnClickListener {
            val dicts = dictList[position]
            dicts.expandable = !dicts.expandable
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return dictList.size
    }
}