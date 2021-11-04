package com.example.sopt_assignment_dabin


import HorizontalItemDecorator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sopt_assignment_dabin.Follower.FollowerResponseData
import com.example.sopt_assignment_dabin.databinding.FragmentFollowerRecyclerViewBinding
import com.example.sopt_assignment_dabin.Follower.FollowerAdapter
import com.example.sopt_assignment_dabin.Follower.FollowerResponseDataBio
import com.example.sopt_assignment_dabin.GithubNetwork.GithubServiceCreator
import com.example.sopt_assignment_dabin.Repository.RepositoryResponseData
import retrofit2.Response
import retrofit2.http.Body

class FolloweFragment : Fragment() {
    private lateinit var followerAdapter: FollowerAdapter
    private var _binding: FragmentFollowerRecyclerViewBinding? = null
    private val binding get() = _binding!!
    lateinit var followerList: List<FollowerResponseData>
    lateinit var followerBio: MutableList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerRecyclerViewBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initNetwork()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initFollowerAdapter(followerList: List<FollowerResponseData>) {
        binding.container.addItemDecoration(HorizontalItemDecorator(requireActivity(), R.drawable.rectangle_dividegray_width_1, 0, 0, 0))
        followerAdapter = FollowerAdapter(followerList)
        binding.container.adapter = followerAdapter
        followerAdapter.notifyDataSetChanged()
    }


    private fun initNetwork() {

        val callFollower: retrofit2.Call<List<FollowerResponseData>> = GithubServiceCreator.githubService.githubFollowerGet("DroidNinja")

        callFollower.enqueue(object : retrofit2.Callback<List<FollowerResponseData>> {
            override fun onResponse(call: retrofit2.Call<List<FollowerResponseData>>, response: Response<List<FollowerResponseData>>) {
                if (response.isSuccessful) {
                    followerList = response?.body() ?: listOf()
                     initFollowerAdapter(followerList)
                } else {
                    //
                }
            }

            override fun onFailure(call: retrofit2.Call<List<FollowerResponseData>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}

