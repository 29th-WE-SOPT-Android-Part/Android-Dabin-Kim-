package com.example.sopt_assignment_dabin.ViewPager_Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.sopt_assignment_dabin.R
import com.example.sopt_assignment_dabin.ViewPager_Adapter.HomeViewPagerAdapter
import com.example.sopt_assignment_dabin.databinding.FragmentHomeBackgroundViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator


class HomeBackgroundViewPager : Fragment() {
    private var _binding: FragmentHomeBackgroundViewPagerBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewPagerAdapter: HomeViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBackgroundViewPagerBinding.inflate(layoutInflater, container, false)
        initAdapter()
        initTabLayout()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initAdapter() {
        val fragmentList = listOf(FollowingViewPager(), FollowerViewpager())
        homeViewPagerAdapter = HomeViewPagerAdapter(this)
        homeViewPagerAdapter.fragments.addAll(fragmentList)
        binding.vpHome.adapter = homeViewPagerAdapter
    }

    private fun initTabLayout() {
        val tabLable = listOf("팔로잉", "팔로워")
        TabLayoutMediator(binding.tlHome, binding.vpHome) { tab, position ->
            tab.text = tabLable[position]
        }.attach()
    }
}


