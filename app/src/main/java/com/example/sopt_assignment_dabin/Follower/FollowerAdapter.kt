package com.example.sopt_assignment_dabin.Follower

import android.content.Intent
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sopt_assignment_dabin.GithubNetwork.GithubServiceCreator
import com.example.sopt_assignment_dabin.Repository.ContactDiffUtil
import com.example.sopt_assignment_dabin.Repository.RepositoryResponseData
import com.example.sopt_assignment_dabin.databinding.ItemFollowerListBinding
import com.google.android.material.internal.ContextUtils
import retrofit2.Response
import retrofit2.http.Body

class FollowerAdapter(var followerList: MutableList<FollowerResponseDataBio2>) : RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>() {
    inner class FollowerViewHolder(private val view: ItemFollowerListBinding) : RecyclerView.ViewHolder(view.root) {
        fun onBind(data: FollowerResponseDataBio2) {
            view.tvName.text = data.followerId
            view.tvStory.text=data.followerBio
            Glide.with(itemView.context).load(data.followerProfile).circleCrop().into(view.ivFollowerImage)
            itemView.setOnClickListener {
                val intent = Intent(itemView?.context, DetailActivity::class.java).apply {
                    putExtra("name", data.followerId)
                    putExtra("image", data.followerProfile)
                }
                ContextCompat.startActivity(itemView.context, intent, null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerAdapter.FollowerViewHolder {
        val view = ItemFollowerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(view)
    }

    override fun getItemCount() = followerList.size //한줄로 리턴되는 함수 가독성~

    override fun onBindViewHolder(holder: FollowerAdapter.FollowerViewHolder, position: Int) {
        holder.onBind(followerList[position])
        // holder.followerBio = initNetworkBio(followerList[position].followerId)
    }

    fun setContact(contacts: MutableList<FollowerResponseDataBio2>) {
        val diffResult = DiffUtil.calculateDiff(ContactDiffUtil(this.followerList, followerList), false)
        diffResult.dispatchUpdatesTo(this)
        this.followerList = followerList
    }
}

//class FollowerBioAdapter(var followerBioList: MutableList<FollowerResponseDataBio>) : RecyclerView.Adapter<FollowerBioAdapter.FollowerBioViewHolder>() {
//    inner class FollowerBioViewHolder(private val view: ItemFollowerListBinding) : RecyclerView.ViewHolder(view.root) {
//        fun onBind(data: FollowerResponseDataBio) {
//            view.tvStory.text = data.followerBio
//            itemView.setOnClickListener {
//                val intent = Intent(itemView?.context, DetailActivity::class.java).apply {
//                    putExtra("bio", data.followerBio)
//                }
//                ContextCompat.startActivity(itemView.context, intent, null)
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerBioAdapter.FollowerBioViewHolder {
//        val view = ItemFollowerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return FollowerBioViewHolder(view)
//    }
//
//    override fun getItemCount() = followerBioList.size //한줄로 리턴되는 함수 가독성~
//
//    override fun onBindViewHolder(holder: FollowerBioAdapter.FollowerBioViewHolder, position: Int) {
//        holder.onBind(followerBioList[position])
//        // holder.followerBio = initNetworkBio(followerList[position].followerId)
//    }
//
////    fun setContact(contacts: List<FollowerResponseData>) {
////        val diffResult = DiffUtil.calculateDiff(ContactDiffUtilBio(this.followerBioList, followerBioList), false)
////        diffResult.dispatchUpdatesTo(this)
////        this.followerBioList = followerBioList
////    }
//}
//
