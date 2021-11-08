package com.example.sopt_assignment_dabin


import HorizontalItemDecorator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.sopt_assignment_dabin.Follower.*
import com.example.sopt_assignment_dabin.databinding.FragmentFollowerRecyclerViewBinding
import com.example.sopt_assignment_dabin.GithubNetwork.GithubServiceCreator
import com.example.sopt_assignment_dabin.Repository.RepositoryResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body

class FolloweFragment : Fragment() {
    private lateinit var followerAdapter: FollowerAdapter
    private var _binding: FragmentFollowerRecyclerViewBinding? = null
    private val binding get() = _binding!!

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

    private fun initFollowerAdapter(allList: MutableList<FollowerResponseDataBio2>) {
        binding.container.addItemDecoration(HorizontalItemDecorator(requireActivity(), R.drawable.rectangle_dividegray_width_1, 0, 0, 0))
        followerAdapter = FollowerAdapter(allList)
        binding.container.adapter = followerAdapter
        followerAdapter.setContact(followerAdapter.followerList)
    }

//    private fun initFollowerBioAdapter(followerBio: MutableList<FollowerResponseDataBio>) {
//        //     binding.container.addItemDecoration(HorizontalItemDecorator(requireActivity(), R.drawable.rectangle_dividegray_width_1, 0, 0, 0))
//        val followerBioAdapter = FollowerBioAdapter(followerBio)
//        binding.container.adapter = followerBioAdapter
//        followerBioAdapter.notifyDataSetChanged()
//    }


    private fun initNetwork() {
        Log.d("initNetwork", "안녕")
        val callFollower: Call<List<FollowerResponseData>> = GithubServiceCreator.githubService.githubFollowerGet("l2hyunwoo")
        callFollower.enqueue(object : Callback<List<FollowerResponseData>> {
            override fun onResponse(call: Call<List<FollowerResponseData>>, response: Response<List<FollowerResponseData>>) {
                Log.d("initNetwork", "ff")
                if (response.isSuccessful) {
                    Log.d("응답성공", "안녕")
                    val followerList = response?.body() ?: listOf()
                  //  bioNetwork(followerList)
                } else {
                    Log.d("successfully connect with Server", "하이")
                }
            }

            override fun onFailure(call: Call<List<FollowerResponseData>>, t: Throwable) {
                Log.d("failed with connection Server", t.message.toString())
            }
        })
    }

    fun bioNetwork(followerList: List<FollowerResponseData>?) {
        Log.d("bioNetwork", "bioNetwork성공")
        var idList = mutableListOf<String>()
        var bioList = mutableListOf<String>()
        var allList = mutableListOf<FollowerResponseDataBio2>()
        for (item in 0..followerList!!.size - 1) {
            idList.add(followerList[item]?.followerId ?: "")
            Log.d("ww", "${followerList[item].followerId}")
        }

        for (item in 0..followerList.size - 1) {
            lateinit var bio: FollowerResponseDataBio
            val callFollowerBio: Call<FollowerResponseDataBio> = GithubServiceCreator.githubService.githubBioGet("${idList.get(item)}")

            callFollowerBio.enqueue(object : Callback<FollowerResponseDataBio> {
                override fun onResponse(call: Call<FollowerResponseDataBio>, response: Response<FollowerResponseDataBio>) {
                    if (response.isSuccessful) {
                        bio = response?.body() ?: FollowerResponseDataBio("")
                        Log.d("bio", "$bio")
                        bioList.add(bio.toString())
                    } else {
                        Log.d("successfully connect with Server", response.body().toString())
                    }
                }

                override fun onFailure(call: Call<FollowerResponseDataBio>, t: Throwable) {
                    Log.d("failed with connection Server", t.message.toString())
                }
            })

        }
        for (item in 0..followerList.size - 1) {
            allList.add(FollowerResponseDataBio2(followerList[item].followerId, followerList[item].followerProfile, " "))
            Toast.makeText(requireContext(), "${allList.get(item).followerBio},${allList.get(item).followerBio}", Toast.LENGTH_SHORT).show()
        }
        initFollowerAdapter(allList)
    }
}
