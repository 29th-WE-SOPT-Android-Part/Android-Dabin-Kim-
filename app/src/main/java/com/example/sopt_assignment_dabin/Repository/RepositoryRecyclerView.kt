package com.example.sopt_assignment_dabin

import HorizontalItemDecorator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sopt_assignment_dabin.Repository.RepositoryListData
import com.example.sopt_assignment_dabin.Repository.RepositoryRecyclerViewAdapter
import com.example.sopt_assignment_dabin.databinding.FragmentRepositoryRecyclerViewBinding

class RepositoryRecyclerView : Fragment() {
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
        super.onViewCreated(view, savedInstanceState)
        initRepositoryAdapter()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun initRepositoryAdapter() {
        val repositoryAdapter = RepositoryRecyclerViewAdapter()
        binding.container.adapter = repositoryAdapter
        binding.container.layoutManager = LinearLayoutManager(requireActivity())  //activity, RecyclerView.VERTICAL, false
        binding.container.addItemDecoration(HorizontalItemDecorator(requireActivity(), R.drawable.recyclerview_line_divider, 0, 0, 0))
        ItemTouchHelper(ItemTouchHelperCallback(repositoryAdapter)).attachToRecyclerView(binding.container)

        repositoryAdapter.repositoryList.addAll(
            listOf(
                RepositoryListData("안드로이드 레파지토리", "하이"),
                RepositoryListData("웹 레파지토리", "하이"),
                RepositoryListData("아요 레파지토리", "하이"),
                RepositoryListData("엥 레파지토리", "하이"),
                RepositoryListData("서버 레파지토리", "하이"),
                RepositoryListData("배고흔 레파지토리", "하이"),
                RepositoryListData("얼랄라 레파지토리", "하이")
            )
        )
        repositoryAdapter.notifyDataSetChanged()
    }
}




