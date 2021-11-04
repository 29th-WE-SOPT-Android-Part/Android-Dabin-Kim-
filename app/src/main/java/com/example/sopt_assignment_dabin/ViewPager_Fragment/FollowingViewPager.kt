package com.example.sopt_assignment_dabin.ViewPager_Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sopt_assignment_dabin.R
import com.example.sopt_assignment_dabin.databinding.FragmentFollowerViewpagerBinding
import com.example.sopt_assignment_dabin.databinding.FragmentFollowingViewPagerBinding
import com.example.sopt_assignment_dabin.databinding.FragmentHomeBackgroundViewPagerBinding


class FollowingViewPager : Fragment() {
    private var _binding: FragmentFollowingViewPagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowingViewPagerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}