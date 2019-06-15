package com.nadia.totoro.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 *
 * author: Created by 闹闹 on 2018-09-19
 * version: 1.0.0
 */
class TabFragmentAdapter(fm: FragmentManager, private val mFragments: List<Fragment>) : FragmentPagerAdapter(fm) {

	override fun getItem(position: Int): Fragment = mFragments[position]

	override fun getCount(): Int = mFragments.size
}
