package com.sungdonggu.naverapi.TabLayoutPager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.google.android.material.tabs.TabLayout
import com.sungdonggu.naverapi.BottomNav.Dict.RealDictFragment
import com.sungdonggu.naverapi.R
import kotlinx.android.synthetic.main.activity_tab_pager.*

class TabPagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_pager)

        // create new tab
        tab_layout.addTab(tab_layout.newTab().setText("경제용어사전"))
        tab_layout.addTab(tab_layout.newTab().setText("즐겨찾기"))

        val pagerAdapter = PagerAdapter(supportFragmentManager, 2)
        view_pager.adapter = pagerAdapter

        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                // 탭이 클릭되었을 때 순번과 맞는 프라그먼트를 보여주면 된다.
                view_pager.currentItem = tab!!.position

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
        // 페이저가 이동했을 때 탭을 이동시키는 코드
        view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))
    }
}

class PagerAdapter(
    //수평으로 보여줄 화면들은 프라그먼트로 구성되어있다.
    fragmentManager: FragmentManager,
    val tabCount: Int
) : FragmentStatePagerAdapter(fragmentManager) {
    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return RealDictFragment()
            }
            1 -> {
                return Fragment2()
            }
            else -> {
                return RealDictFragment()
            }
        }

    }
}