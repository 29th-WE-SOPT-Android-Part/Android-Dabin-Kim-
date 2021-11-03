package com.example.sopt_assignment_dabin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.view.menu.MenuView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sopt_assignment_dabin.databinding.FragmentFollowerRecyclerViewBinding
import com.example.sopt_assignment_dabin.databinding.ItemFollowerListBinding
import com.example.sopt_assignment_dabin.databinding.ItemSampleListBinding

class FollowerRecyclerView : Fragment() {
    private lateinit var followerAdapter: FollowerRecyclerViewAdapter
    private var _binding: FragmentFollowerRecyclerViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerRecyclerViewBinding.inflate(layoutInflater, container, false)
        initFollowerAdapter()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun initFollowerAdapter() {
        val activity = context as Context
        followerAdapter = FollowerRecyclerViewAdapter(activity)
        binding.container.adapter = followerAdapter
        binding.container.layoutManager = GridLayoutManager(activity, 2)  //activity, RecyclerView.VERTICAL, false
        followerAdapter.followerList.addAll(
            listOf(
                Introduce_SOPT("김다빈", "하이봉쥬르올라라라메로나 히으아리ㅏ하하하이ㅑㅓ라ㅣㅓ나어러ㅏ어라"),
                Introduce_SOPT("백다빈", "반갑습니다"),
                Introduce_SOPT("안다빈", "오하이오"),
                Introduce_SOPT("김연아", "111111하이"),
                Introduce_SOPT("박지성", "22222하이"),
                Introduce_SOPT("김다빈", "3333하이"),
                Introduce_SOPT("김다빈", "4444하이"),
                Introduce_SOPT("김다빈", "555하이")
            )

        )
        followerAdapter.notifyDataSetChanged()
    }
}

class FollowerRecyclerViewAdapter(val activity: Context) : RecyclerView.Adapter<FollowerRecyclerViewAdapter.FollowerViewHolder>() {
    val followerList = mutableListOf<Introduce_SOPT>()

    inner class FollowerViewHolder(private val view: ItemFollowerListBinding) : RecyclerView.ViewHolder(view.root) {
        fun onBind(data: Introduce_SOPT) {
            view.tvName.text = data.name
            view.tvStory.text = data.story

            itemView.setOnClickListener { view: View ->
                val intent = Intent(view.context, DetailActivity::class.java)
               intent.putExtra("name", data.name)
                view.context.startActivity(intent)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerRecyclerViewAdapter.FollowerViewHolder {
        val view = ItemFollowerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(view)
    }

    override fun getItemCount() = followerList.size //한줄로 리턴되는 함수 가독성~

    override fun onBindViewHolder(holder: FollowerRecyclerViewAdapter.FollowerViewHolder, position: Int) {
        holder.onBind(followerList[position])


    }


}


