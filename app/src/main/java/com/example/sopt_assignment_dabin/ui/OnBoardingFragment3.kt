package com.example.sopt_assignment_dabin.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.sopt_assignment_dabin.R
import com.example.sopt_assignment_dabin.databinding.FragmentOnBoarding1Binding
import com.example.sopt_assignment_dabin.databinding.FragmentOnBoarding3Binding

class OnBoardingFragment3 : Fragment() {
    private var _binding: FragmentOnBoarding3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnBoarding3Binding.inflate(layoutInflater, container, false)

        binding.btNext.setOnClickListener {
            val intent = Intent(requireContext(), SignInActivity::class.java)
            ContextCompat.startActivity(requireContext(), intent, null)
            requireActivity().finish()
        }


        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}