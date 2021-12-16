package com.example.sopt_assignment_dabin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sopt_assignment_dabin.R
import com.example.sopt_assignment_dabin.databinding.FragmentOnBoarding1Binding
import com.example.sopt_assignment_dabin.databinding.FragmentOnBoarding2Binding

class OnBoardingFragment2 : Fragment() {
    private var _binding: FragmentOnBoarding2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnBoarding2Binding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btNext.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardingFragment2_to_onBoardingFragment3)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}