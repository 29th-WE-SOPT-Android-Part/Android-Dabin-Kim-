package com.example.sopt_assignment_dabin.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.sopt_assignment_dabin.ui.FollowerFragment
import com.example.sopt_assignment_dabin.R
import com.example.sopt_assignment_dabin.ui.RepositoryFragment
import com.example.sopt_assignment_dabin.databinding.FragmentProfileBackgroundViewpagerBinding


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBackgroundViewpagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBackgroundViewpagerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        drawProfileImage()
        transactionEvent()
        clickPreference()
    }

    private fun transactionEvent() {
        binding.bvFollower.isSelected = true
        val repositoryRecyclerViewFragment = RepositoryFragment()
        val followerRecyclerViewFragment = FollowerFragment()
        childFragmentManager
            .beginTransaction()
            .add(R.id.home_container, followerRecyclerViewFragment)
            .commit()


        binding.bvFollower.setOnClickListener {
            binding.bvRepository.isSelected = false
            binding.bvFollower.isSelected = true
            childFragmentManager
                .beginTransaction()
                .replace(R.id.home_container, followerRecyclerViewFragment)
                .commit()
        }


        binding.bvRepository.setOnClickListener {
            binding.bvFollower.isSelected = false
            binding.bvRepository.isSelected = true
            childFragmentManager
                .beginTransaction()
                .replace(R.id.home_container, repositoryRecyclerViewFragment)
                .commit()
        }
    }

    private fun drawProfileImage() {
        Glide.with(this)
            .load(R.drawable.img_home_user)
            .circleCrop()
            .into(binding.ivProfile)
    }

    private fun clickPreference() {
        binding.ivPreference.setOnClickListener {
            val intent = Intent(requireContext(), PreferenceActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
