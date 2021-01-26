package com.sungdonggu.naverapi.BottomNav.Dict

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sungdonggu.naverapi.FirebaseDictionary.FirebaseDictionary
import com.sungdonggu.naverapi.R
import kotlinx.android.synthetic.main.item_dict.view.*

class RealDictAdapter(var fragDictList: ArrayList<RealDictionary>) :
    RecyclerView.Adapter<RealDictAdapter.fragDictVH>() {
    inner class fragDictVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var fragWord = itemView.tv_word
        var fragContent = itemView.tv_def
        var fragLinearLayout = itemView.dict_expandable_linearlayout
        var fragExpandable_layout = itemView.dict_expandable_layout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): fragDictVH {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_dict, parent, false)
        return fragDictVH(view)
    }

    override fun onBindViewHolder(holder: fragDictVH, position: Int) {
        holder.fragWord.text = fragDictList[position].word
        holder.fragContent.text = fragDictList[position].content

        holder.fragExpandable_layout.visibility =
            if (fragDictList[position].expandable) View.VISIBLE else View.GONE
        holder.fragLinearLayout.setOnClickListener {
            val dicts = fragDictList[position]
            dicts.expandable = !dicts.expandable
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return fragDictList.size
    }
}