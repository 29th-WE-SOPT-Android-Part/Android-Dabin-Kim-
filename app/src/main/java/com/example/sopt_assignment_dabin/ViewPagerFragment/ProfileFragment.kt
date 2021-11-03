package com.example.sopt_assignment_dabin.ViewPagerFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.sopt_assignment_dabin.FolloweFragment
import com.example.sopt_assignment_dabin.R
import com.example.sopt_assignment_dabin.RepositoryFragment
import com.example.sopt_assignment_dabin.databinding.FragmentProfileBackgroundViewpagerBinding


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBackgroundViewpagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBackgroundViewpagerBinding.inflate(layoutInflater, container, false)
        drawProfileImage()
        transactionEvent()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun transactionEvent() {
        binding.bvFollower.isSelected = true
        val repositoryRecyclerViewFragment = RepositoryFragment()
        val followerRecyclerViewFragment = FolloweFragment()
        childFragmentManager
            .beginTransaction()
            .add(R.id.home_container, followerRecyclerViewFragment)
            .commit()

        binding.bvFollower.setOnClickListener {
            if (binding.bvRepository.isSelected == true) {
                binding.bvRepository.isSelected = false
                binding.bvFollower.isSelected = true
                childFragmentManager
                    .beginTransaction()
                    .replace(R.id.home_container, followerRecyclerViewFragment)
                    .commit()
            } else {
                childFragmentManager
                    .beginTransaction()
                    .replace(R.id.home_container, followerRecyclerViewFragment)
                    .commit()
            }
        }

        binding.bvRepository.setOnClickListener {
            if (binding.bvFollower.isSelected == true) {
                binding.bvFollower.isSelected = false
                binding.bvRepository.isSelected = true
                childFragmentManager
                    .beginTransaction()
                    .replace(R.id.home_container, repositoryRecyclerViewFragment)
                    .commit()
            } else {
                childFragmentManager
                    .beginTransaction()
                    .replace(R.id.home_container, repositoryRecyclerViewFragment)
                    .commit()
            }
        }
    }

    private fun drawProfileImage() {
        Glide.with(this)
            .load(R.drawable.img_home_user)
            .circleCrop()
            .into(binding.ivProfile)
    }
}
