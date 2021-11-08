package com.example.sopt_assignment_dabin

import HorizontalItemDecorator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sopt_assignment_dabin.GithubNetwork.GithubServiceCreator
import com.example.sopt_assignment_dabin.Repository.RepositoryResponseData
import com.example.sopt_assignment_dabin.Repository.RepositoryAdapter
import com.example.sopt_assignment_dabin.databinding.FragmentRepositoryRecyclerViewBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryFragment : Fragment() {
    private var _binding: FragmentRepositoryRecyclerViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepositoryRecyclerViewBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initNetwork()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun initRepositoryAdapter(list: List<RepositoryResponseData>) {
        val repositoryAdapter = RepositoryAdapter(list)
        binding.container.adapter = repositoryAdapter
        binding.container.layoutManager = LinearLayoutManager(requireActivity())
        binding.container.addItemDecoration(HorizontalItemDecorator(requireActivity(), R.drawable.rectangle_dividegray_width_1, 0, 0, 0))
        // ItemTouchHelper(ItemTouchHelperCallback(repositoryAdapter)).attachToRecyclerView(binding.container)
        repositoryAdapter.setContact(repositoryAdapter.repositoryList)
    }

    private fun initNetwork() {

        val call: Call<List<RepositoryResponseData>> = GithubServiceCreator.githubService.githubRepoGet("dabinKim-0318")

        call.enqueue(object : Callback<List<RepositoryResponseData>> {
            override fun onResponse(call: Call<List<RepositoryResponseData>>, response: Response<List<RepositoryResponseData>>) {
                if (response.isSuccessful) {  //응답은 성공적인데 body가 null일수도 있는거 항상 고려
                    var data = response?.body() ?: listOf()
                    initRepositoryAdapter(data)
                } else {
                    Log.d("successfully connect with Server", response.body().toString())
                }
            }
            override fun onFailure(call: Call<List<RepositoryResponseData>>, t: Throwable) {
                Log.d("failed with connection Server", t.message.toString())
            }
        })
    }
}





