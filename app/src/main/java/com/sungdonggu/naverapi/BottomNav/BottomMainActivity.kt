package com.sungdonggu.naverapi.BottomNav

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sungdonggu.naverapi.R
import kotlinx.android.synthetic.main.activity_bottom_main.*

class BottomMainActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var homeFragment: HomeFragment
    private lateinit var newsFragment: NewsFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_main)
        bottom_nav.setOnNavigationItemSelectedListener(this)
        homeFragment = HomeFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.frame_for_fragment, homeFragment)
            .commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                homeFragment = HomeFragment.newInstance()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_for_fragment, homeFragment).commit()
            }
            R.id.nav_news -> {
                newsFragment = NewsFragment.newInstance()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_for_fragment, newsFragment).commit()
            }
        }
        return true
    }
}