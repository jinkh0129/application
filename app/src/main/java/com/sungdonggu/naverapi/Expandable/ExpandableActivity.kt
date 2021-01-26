package com.sungdonggu.naverapi.Expandable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sungdonggu.naverapi.R
import kotlinx.android.synthetic.main.activity_expandable.*

class ExpandableActivity : AppCompatActivity() {

    val versionList = ArrayList<Versions>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expandable)
        initData()
        setRecyclerView()
    }

    private fun initData() {
        versionList.add(
            Versions(
                "Kit Kat",
                "Version: 4.4 ~ 4.4.4",
                "API Level : 19",
                "Android development releases are organized into familiar....."
            )
        )
        versionList.add(
            Versions(
                "Kit Kat",
                "Version: 4.4 ~ 4.4.4",
                "API Level : 19",
                "Android development releases are organized into familiar....."
            )
        )
        versionList.add(
            Versions(
                "Kit Kat",
                "Version: 4.4 ~ 4.4.4",
                "API Level : 19",
                "Android development releases are organized into familiar....."
            )
        )
        versionList.add(
            Versions(
                "Kit Kat",
                "Version: 4.4 ~ 4.4.4",
                "API Level : 19",
                "Android development releases are organized into familiar....."
            )
        )
        versionList.add(
            Versions(
                "Kit Kat",
                "Version: 4.4 ~ 4.4.4",
                "API Level : 19",
                "Android development releases are organized into familiar....."
            )
        )
        versionList.add(
            Versions(
                "Kit Kat",
                "Version: 4.4 ~ 4.4.4",
                "API Level : 19",
                "Android development releases are organized into familiar....."
            )
        )
        versionList.add(
            Versions(
                "Kit Kat",
                "Version: 4.4 ~ 4.4.4",
                "API Level : 19",
                "Android development releases are organized into familiar....."
            )
        )
        versionList.add(
            Versions(
                "Kit Kat",
                "Version: 4.4 ~ 4.4.4",
                "API Level : 19",
                "Android development releases are organized into familiar....."
            )
        )
        versionList.add(
            Versions(
                "Kit Kat",
                "Version: 4.4 ~ 4.4.4",
                "API Level : 19",
                "Android development releases are organized into familiar....."
            )
        )
        versionList.add(
            Versions(
                "Kit Kat",
                "Version: 4.4 ~ 4.4.4",
                "API Level : 19",
                "Android development releases are organized into familiar....."
            )
        )
        versionList.add(
            Versions(
                "Kit Kat",
                "Version: 4.4 ~ 4.4.4",
                "API Level : 19",
                "Android development releases are organized into familiar....."
            )
        )
        versionList.add(
            Versions(
                "Kit Kat",
                "Version: 4.4 ~ 4.4.4",
                "API Level : 19",
                "Android development releases are organized into familiar....."
            )
        )
        versionList.add(
            Versions(
                "Kit Kat",
                "Version: 4.4 ~ 4.4.4",
                "API Level : 19",
                "Android development releases are organized into familiar....."
            )
        )
        versionList.add(
            Versions(
                "Kit Kat",
                "Version: 4.4 ~ 4.4.4",
                "API Level : 19",
                "Android development releases are organized into familiar....."
            )
        )
        versionList.add(
            Versions(
                "Kit Kat",
                "Version: 4.4 ~ 4.4.4",
                "API Level : 19",
                "Android development releases are organized into familiar....."
            )
        )
        versionList.add(
            Versions(
                "Kit Kat",
                "Version: 4.4 ~ 4.4.4",
                "API Level : 19",
                "Android development releases are organized into familiar....."
            )
        )

    }

    private fun setRecyclerView() {
        val versionAdapter = VersionAdapter(versionList)
        expandable_recyclerView.adapter = versionAdapter
        expandable_recyclerView.setHasFixedSize(true)
    }
}