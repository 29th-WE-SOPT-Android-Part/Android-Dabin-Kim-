package com.example.sopt_assignment_dabin.ui


import com.example.sopt_assignment_dabin.util.HorizontalItemDecorator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sopt_assignment_dabin.R
import com.example.sopt_assignment_dabin.databinding.FragmentFollowerRecyclerViewBinding
import com.example.sopt_assignment_dabin.ui.adapter.FollowerAdapter
import com.example.sopt_assignment_dabin.follower.FollowerData
import com.example.sopt_assignment_dabin.follower.FollowerResponseData
import com.example.sopt_assignment_dabin.follower.FollowerResponseDataBio
import com.example.sopt_assignment_dabin.github.GithubServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerFragment : Fragment() {
    private var _binding: FragmentFollowerRecyclerViewBinding? = null
    private val binding get() = _binding!!
    private lateinit var followerAdapter: FollowerAdapter
    private val idList = mutableListOf<String>()
    private val bioList = mutableListOf<String>()
    private val allList = mutableListOf<FollowerData>()
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

    private fun initFollowerAdapter(allList: MutableList<FollowerData>) {
        followerAdapter = FollowerAdapter(allList)
        binding.container.adapter = followerAdapter
        binding.container.layoutManager = LinearLayoutManager(requireActivity())
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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
