package com.example.sopt_assignment_dabin.Follower

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sopt_assignment_dabin.databinding.ItemFollowerListBinding

class FollowerAdapter(var followerList: MutableList<FollowerData>) : RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>() {
    inner class FollowerViewHolder(private val view: ItemFollowerListBinding) : RecyclerView.ViewHolder(view.root) {
        fun onBind(data: FollowerData) {
            view.tvName.text = data.followerId
            view.tvStory.text=data.followerBio
            Glide.with(itemView.context).load(data.followerProfile).circleCrop().into(view.ivFollowerImage)
            itemView.setOnClickListener {
                val intent = Intent(itemView?.context, DetailActivity::class.java).apply {
                    putExtra("name", data.followerId)
                    putExtra("image", data.followerProfile)
                    putExtra("bio", data.followerBio)
                }
                ContextCompat.startActivity(itemView.context, intent, null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerAdapter.FollowerViewHolder {
        val view = ItemFollowerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(view)
    }

    override fun getItemCount() = followerList.size

    override fun onBindViewHolder(holder: FollowerAdapter.FollowerViewHolder, position: Int) {
        holder.onBind(followerList[position])
    }

    fun setContact(contacts: MutableList<FollowerData>) {
        val diffResult = DiffUtil.calculateDiff(ContactDiffUtil(this.followerList, followerList), false)
        diffResult.dispatchUpdatesTo(this)
        this.followerList = followerList
    }
}
