package com.nadia.totoro.sample

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import com.nadia.totoro.adapter.TabFragmentAdapter
import kotlinx.android.synthetic.main.activity_main.*

/**
 *
 * author: Created by 闹闹 on 2019/6/14
 * version: 1.0.0
 */
class MainActivity : BaseActivity<MainActivity>() {

    override fun initLayout(): Int = R.layout.activity_main

    override fun initParameter(bundle: Bundle?) {}

    override fun afterInjectView() {
        initView()
    }

    private fun initView() {
        val fragments = mutableListOf<Fragment>()
        fragments.add(0, CoreWorkFragment().newInstance(0))
        fragments.add(1, TwoFragment().newInstance(1))
        fragments.add(2, ThreeFragment().newInstance(2))

        viewPager.setEnableScroll(false) // false不可滑动
        viewPager.offscreenPageLimit = 1
        viewPager.adapter = TabFragmentAdapter(supportFragmentManager, fragments)
        viewPager.addOnPageChangeListener(onPageChangeListener)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    /**
     * 底部控制
     */
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        // 进行点击事件后的逻辑操作
        when (item.itemId) {
            R.id.navigation_home -> {
                viewPager.currentItem = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                viewPager.currentItem = 1
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                viewPager.currentItem = 2
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private val onPageChangeListener = object : ViewPager.OnPageChangeListener {

        override fun onPageScrollStateChanged(state: Int) {}

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

        override fun onPageSelected(position: Int) {}
    }
}
