package com.sungdonggu.naverapi.Expandable

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sungdonggu.naverapi.R
import kotlinx.android.synthetic.main.expandable_row.view.*

class VersionAdapter(val versionList: List<Versions>) :
    RecyclerView.Adapter<VersionAdapter.VersionVH>() {
    class VersionVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var codeNameTxt: TextView
        var versionTxt: TextView
        var apiLevelTxt: TextView
        var descriptionTxt: TextView
        var linearLayout: LinearLayout
        var expandable_layout: RelativeLayout

        init {
            codeNameTxt = itemView.findViewById(R.id.code_name)
            versionTxt = itemView.findViewById(R.id.version)
            apiLevelTxt = itemView.findViewById(R.id.api_level)
            descriptionTxt = itemView.findViewById(R.id.description)
            linearLayout = itemView.findViewById(R.id.expandable_linearLayout)
            expandable_layout = itemView.findViewById(R.id.expandable_layout)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VersionVH {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.expandable_row, parent, false)
        return VersionVH(view)
    }

    override fun onBindViewHolder(holder: VersionVH, position: Int) {

        holder.codeNameTxt.text = versionList[position].codeName
        holder.versionTxt.text = versionList[position].version
        holder.apiLevelTxt.text = versionList[position].apiLevel
        holder.descriptionTxt.text = versionList[position].description

        holder.expandable_layout.visibility = if (versionList[position].expandable) View.VISIBLE else View.GONE
        holder.linearLayout.setOnClickListener {
            val versions = versionList[position]
            versions.expandable = !versions.expandable
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return versionList.size
    }
}