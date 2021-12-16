package com.example.sopt_assignment_dabin.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.sopt_assignment_dabin.R
import com.example.sopt_assignment_dabin.databinding.FragmentOnBoarding1Binding
import com.example.sopt_assignment_dabin.databinding.FragmentOnBoarding3Binding

class OnBoardingFragment3 : Fragment() {
    private var _binding: FragmentOnBoarding3Binding? = null
    private val binding get() = _binding!!
    private lateinit var callback: OnBackPressedCallback

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnBoarding3Binding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btNext.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardingFragment3_to_signInActivity)
            AutoLogin.setOnBoarding(requireContext(), false)
            requireActivity().finish()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_onBoardingFragment3_to_onBoardingFragment1)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}