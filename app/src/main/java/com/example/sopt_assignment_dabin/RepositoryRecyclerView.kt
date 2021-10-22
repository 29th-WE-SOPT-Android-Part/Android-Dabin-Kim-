package com.example.sopt_assignment_dabin

import VerticalItemDecorator
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.example.sopt_assignment_dabin.databinding.*

class RepositoryRecyclerView : Fragment() {
    private lateinit var repositoryAdapter: RepositoryRecyclerViewAdapter
    private var _binding: FragmentRepositoryRecyclerViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepositoryRecyclerViewBinding.inflate(layoutInflater, container, false)
        initRepositoryAdapter()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun initRepositoryAdapter() {
        val activity = context as Activity
        repositoryAdapter = RepositoryRecyclerViewAdapter()
        binding.container.adapter = repositoryAdapter
        binding.container.layoutManager = LinearLayoutManager(activity)  //activity, RecyclerView.VERTICAL, false
        binding.container.addItemDecoration(VerticalItemDecorator(activity, R.drawable.repository_line_divider, 60, 60, 30))
        ItemTouchHelper(ItemTouchHelperCallback(repositoryAdapter)).attachToRecyclerView(binding.container)

        repositoryAdapter.followerList.addAll(
            listOf(
                Introduce_SOPT("안드로이드 레파지토리", "하이"),
                Introduce_SOPT("웹 레파지토리", "하이"),
                Introduce_SOPT("아요 레파지토리", "하이"),
                Introduce_SOPT("엥 레파지토리", "하이"),
                Introduce_SOPT("서버 레파지토리", "하이"),
                Introduce_SOPT("배고흔 레파지토리", "하이"),
                Introduce_SOPT("얼랄라 레파지토리", "하이")
            )

        )
        repositoryAdapter.notifyDataSetChanged()
    }

}


class RepositoryRecyclerViewAdapter() : RecyclerView.Adapter<RepositoryRecyclerViewAdapter.RepositoryViewHolder>(), ItemTouchHelperListener {
    val followerList = mutableListOf<Introduce_SOPT>()

    override fun onItemMove(from_position: Int, to_position: Int): Boolean {
        val item = followerList[from_position]
        followerList.removeAt(from_position)
        followerList.add(to_position, item)
        notifyDataSetChanged()
        return true
    }

    override fun onItemSwipe(position: Int) {
        followerList.removeAt(position)
        notifyItemRemoved(position)
    }

    class RepositoryViewHolder(private val view: ItemRepositoryListBinding) : RecyclerView.ViewHolder(view.root) {
        fun onBind(data: Introduce_SOPT) {
            view.tvName.text = data.name
            view.tvStory.text = data.story

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = ItemRepositoryListBinding.inflate(LayoutInflater.from(parent.context))
        return RepositoryViewHolder(view)
    }

    override fun getItemCount() = followerList.size //한줄로 리턴되는 함수 가독성~

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.onBind(followerList[position])
    }

}
