package com.example.sopt_assignment_dabin


import HorizontalItemDecorator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sopt_assignment_dabin.Follower.FollowerAdapter
import com.example.sopt_assignment_dabin.Follower.FollowerData
import com.example.sopt_assignment_dabin.Follower.FollowerResponseData
import com.example.sopt_assignment_dabin.Follower.FollowerResponseDataBio
import com.example.sopt_assignment_dabin.GithubNetwork.GithubServiceCreator
import com.example.sopt_assignment_dabin.databinding.FragmentFollowerRecyclerViewBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FolloweFragment : Fragment() {
    private var _binding: FragmentFollowerRecyclerViewBinding? = null
    private val binding get() = _binding!!
    private lateinit var followerAdapter: FollowerAdapter
    private var idList = mutableListOf<String>()
    private var bioList = mutableListOf<String>()
    private var allList = mutableListOf<FollowerData>()
    private lateinit var followerList: List<FollowerResponseData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerRecyclerViewBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bioList.clear()
        initNetwork()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initFollowerAdapter(allList: MutableList<FollowerData>) {
        followerAdapter = FollowerAdapter(allList)
        binding.container.adapter = followerAdapter
        binding.container.addItemDecoration(HorizontalItemDecorator(requireActivity(), R.drawable.rectangle_dividegray_width_1, 0, 0, 0))
        followerAdapter.setContact(followerAdapter.followerList)
    }

    private fun initNetwork() {
        val callFollower: Call<List<FollowerResponseData>> = GithubServiceCreator.githubService.githubFollowerGet("dabinKim-0318")
        callFollower.enqueue(object : Callback<List<FollowerResponseData>> {
            override fun onResponse(call: Call<List<FollowerResponseData>>, response: Response<List<FollowerResponseData>>) {
                if (response.isSuccessful) {
                    followerList = response?.body() ?: listOf()
                    bioNetwork(followerList)
                } else {
                    Log.d("successfully connect with Server", "${response?.body()}")
                }
            }

            override fun onFailure(call: Call<List<FollowerResponseData>>, t: Throwable) {
                Log.d("failed with connection Server", t.message.toString())
            }
        })
    }

    private fun bioNetwork(followerList: List<FollowerResponseData>) {
        for (item in followerList) {
            idList.add(item.followerId)
        }

        for (item in idList) {
            var bio: String

            val callFollowerBio: Call<FollowerResponseDataBio> = GithubServiceCreator.githubService.githubBioGet("$item")
            callFollowerBio.enqueue(object : Callback<FollowerResponseDataBio> {
                override fun onResponse(call: Call<FollowerResponseDataBio>, response: Response<FollowerResponseDataBio>) {
                    if (response.isSuccessful) {
                        bio = response.body()?.bio ?: ""
                        bioList.add(bio)
                    } else {
                        Log.d("successfully connect with Server", response.body().toString())
                    }
                }

                override fun onFailure(call: Call<FollowerResponseDataBio>, t: Throwable) {
                    Log.d("failed with connection Server", t.message.toString())
                }
            })
        }
        if (bioList.size != 0) {
            connectAdapter()
        }
    }

    private fun connectAdapter() {
        for (item in 0 until followerList.size) {
            allList.add(FollowerData(followerList[item].followerId, followerList[item].followerProfile, bioList[item]))
        }
        initFollowerAdapter(allList)
    }
}
