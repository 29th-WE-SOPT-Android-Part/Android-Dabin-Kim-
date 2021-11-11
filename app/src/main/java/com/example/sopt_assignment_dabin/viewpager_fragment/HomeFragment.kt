package com.example.sopt_assignment_dabin.viewpager_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sopt_assignment_dabin.viewpager_adapter.HomeViewPagerAdapter
import com.example.sopt_assignment_dabin.databinding.FragmentHomeBackgroundViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : Fragment() {
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

    private fun initAdapter() {
        val fragmentList = listOf(FollowingFragment(), FollowerFragment())
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}


