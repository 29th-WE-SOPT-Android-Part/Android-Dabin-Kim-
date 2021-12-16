package com.example.sopt_assignment_dabin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sopt_assignment_dabin.R
import com.example.sopt_assignment_dabin.databinding.FragmentFollowingViewPagerBinding
import com.example.sopt_assignment_dabin.databinding.FragmentOnBoarding1Binding

class OnBoardingFragment1 : Fragment() {
    private var _binding: FragmentOnBoarding1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnBoarding1Binding.inflate(layoutInflater, container, false)

        binding.btNext.setOnClickListener {
           findNavController().navigate(R.id.action_onBoardingFragment1_to_onBoardingFragment2)
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}