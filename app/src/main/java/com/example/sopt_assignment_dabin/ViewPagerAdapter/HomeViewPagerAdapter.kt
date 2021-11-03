package com.example.sopt_assignment_dabin.ViewPagerAdapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    val fragments = mutableListOf<Fragment>()
    override fun createFragment(position: Int): Fragment = fragments[position]
    override fun getItemCount(): Int = fragments.size
}