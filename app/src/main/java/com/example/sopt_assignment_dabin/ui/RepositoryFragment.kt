package com.example.sopt_assignment_dabin.ui

import android.content.Intent
import com.example.sopt_assignment_dabin.util.HorizontalItemDecorator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sopt_assignment_dabin.R
import com.example.sopt_assignment_dabin.github.GithubServiceCreator
import com.example.sopt_assignment_dabin.data.local.RepositoryResponseData
import com.example.sopt_assignment_dabin.data.local.SigninRequestData
import com.example.sopt_assignment_dabin.ui.adapter.RepositoryAdapter
import com.example.sopt_assignment_dabin.databinding.FragmentRepositoryRecyclerViewBinding
import com.example.sopt_assignment_dabin.sopt.SignServiceCreator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

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

    private fun initRepositoryAdapter(list: List<RepositoryResponseData>) {
        val repositoryAdapter = RepositoryAdapter(list)
        binding.container.adapter = repositoryAdapter
        binding.container.layoutManager = LinearLayoutManager(requireActivity())
        binding.container.addItemDecoration(HorizontalItemDecorator(requireActivity(), R.drawable.rectangle_dividegray_width_1, 0, 0, 0))
        repositoryAdapter.setContact(repositoryAdapter.repositoryList)
    }

    private fun initNetwork() {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = GithubServiceCreator.githubService.githubRepoGet("dabinKim-0318")
                initRepositoryAdapter(response)

            } catch (e: Exception) {
                Log.d("successfully connect with Server", e.toString())
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}





