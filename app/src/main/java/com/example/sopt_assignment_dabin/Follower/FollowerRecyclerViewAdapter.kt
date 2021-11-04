package com.example.sopt_assignment_dabin.Follower

import android.content.Intent
import android.icu.text.IDNA
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sopt_assignment_dabin.R
import com.example.sopt_assignment_dabin.databinding.ItemFollowerListBinding

class FollowerRecyclerViewAdapter : RecyclerView.Adapter<FollowerRecyclerViewAdapter.FollowerViewHolder>() {
    val followerList = mutableListOf<FollowerListData>()

    inner class FollowerViewHolder(private val view: ItemFollowerListBinding) : RecyclerView.ViewHolder(view.root) {
        fun onBind(data: FollowerListData) {
            view.tvName.text = data.name
            view.tvStory.text = data.story
            Glide.with(itemView.context).load(data.image).circleCrop().into(view.ivFollowerImage)

            itemView.setOnClickListener {
                val intent=Intent(itemView?.context,DetailActivity::class.java).apply{
                    putExtra("name", data.name)
                    putExtra("image", data.image)}
                ContextCompat.startActivity(itemView.context,intent,null)
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
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java).apply {
                putExtra("name", followerList[position].name)
                putExtra("image", followerList[position].image)
            }
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
    }
}
