package com.example.sopt_assignment_dabin

import HorizontalItemDecorator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
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

        Toast.makeText(requireContext(),"",Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun initRepositoryAdapter(list: List<RepositoryResponseData.Data>) {
        val repositoryAdapter = RepositoryAdapter(list)
        binding.container.adapter = repositoryAdapter
        binding.container.layoutManager = LinearLayoutManager(requireActivity())  //activity, RecyclerView.VERTICAL, false
        binding.container.addItemDecoration(HorizontalItemDecorator(requireActivity(), R.drawable.rectangle_dividegray_width_1, 0, 0, 0))
        // ItemTouchHelper(ItemTouchHelperCallback(repositoryAdapter)).attachToRecyclerView(binding.container)
        repositoryAdapter.notifyDataSetChanged()
    }

    private fun initNetwork() {

       val call: Call<List<RepositoryResponseData.Data>> = GithubServiceCreator.githubService.githubRepoGet("dabinKim-0318")

        call.enqueue(object : Callback<List<RepositoryResponseData.Data>> {
            override fun onResponse(call: Call<List<RepositoryResponseData.Data>>, response: Response<List<RepositoryResponseData.Data>>) {
                if (response.isSuccessful) {
                    var data = response.body()!!
                    initRepositoryAdapter(data)
                } else {
                    Toast.makeText(requireContext(),"ddd",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<RepositoryResponseData.Data>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}




