package com.sungdonggu.naverapi.BottomNav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sungdonggu.naverapi.BottomNav.Dict.RealDictFragment
import com.sungdonggu.naverapi.BottomNav.News.RealNewsFragment
import com.sungdonggu.naverapi.R
import kotlinx.android.synthetic.main.activity_bottom_nav_level_up.*

class BottomNavLevelUpActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var realDictFragment: RealDictFragment
    private lateinit var realNewsFragment: RealNewsFragment
    private lateinit var realProfileFragment: RealProfileFragment
    private lateinit var realDashboardFragment: RealDashboardFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_nav_level_up)
        real_bottom_nav.setOnNavigationItemSelectedListener(this@BottomNavLevelUpActivity)

        realDictFragment = RealDictFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.main_frame, realDictFragment).commit()
        /**선택된 메뉴들이 안나오고 기본으로 정해준 메뉴만 나오네;;**/

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.real_nav_news -> {
                realNewsFragment = RealNewsFragment.newInstance()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frame, realNewsFragment)
                    .commit()
            }
            R.id.real_nav_dict -> {
                realDictFragment = RealDictFragment.newInstance()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frame, realDictFragment)
                    .commit()
            }
            R.id.real_nav_dashboard -> {
                realDashboardFragment = RealDashboardFragment.newInstance()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frame, realDashboardFragment).commit()
            }
            R.id.real_nav_profile -> {
                realProfileFragment = RealProfileFragment.newInstance()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frame, realProfileFragment).commit()
            }
        }
        return true
    }
}