package com.example.sopt_assignment_dabin.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sopt_assignment_dabin.R
import com.example.sopt_assignment_dabin.data.local.Room.RoomLogin
import com.example.sopt_assignment_dabin.databinding.FragmentOnBoarding3Binding
import com.example.sopt_assignment_dabin.util.MyUtil.getHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OnBoardingFragment3 : Fragment() {
    private var _binding: FragmentOnBoarding3Binding? = null
    private val binding get() = _binding!!
    private lateinit var callback: OnBackPressedCallback

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_onBoardingFragment3_to_onBoardingFragment1)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOnBoarding3Binding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btNext.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    requireActivity().getHelper().insert(RoomLogin(false, false, "", ""))
                } catch (e: Exception) {
                    e.toString()
                }
            }
            findNavController().navigate(R.id.action_onBoardingFragment3_to_signInActivity)
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onDetach() {
        callback.remove()
        super.onDetach()
    }
}