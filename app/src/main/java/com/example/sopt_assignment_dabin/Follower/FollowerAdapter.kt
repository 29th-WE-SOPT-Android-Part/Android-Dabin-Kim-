package com.example.sopt_assignment_dabin.Follower

import android.content.Intent
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sopt_assignment_dabin.GithubNetwork.GithubServiceCreator
import com.example.sopt_assignment_dabin.databinding.ItemFollowerListBinding
import com.google.android.material.internal.ContextUtils
import retrofit2.Response
import retrofit2.http.Body

class FollowerAdapter(val followerList: List<FollowerResponseData>) : RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>() {
    inner class FollowerViewHolder(private val view: ItemFollowerListBinding) : RecyclerView.ViewHolder(view.root) {
        var followerBio = view.tvStory.text
        fun onBind(data: FollowerResponseData) {
            view.tvName.text = data.followerId
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
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java).apply {
                putExtra("name", followerList[position].followerId)
                putExtra("image", followerList[position].followerProfile)
            }
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
    }

//    private fun initNetworkBio(followerId: String): String {
//        lateinit var follower: String
//        val callFollowerBio: retrofit2.Call<FollowerResponseDataBio> = GithubServiceCreator.githubService.githubBioGet("$followerId")
//        callFollowerBio.enqueue(object : retrofit2.Callback<FollowerResponseDataBio> {
//            override fun onResponse(call: retrofit2.Call<FollowerResponseDataBio>, response: Response<FollowerResponseDataBio>) {
//                if (response.isSuccessful) {
//                    follower = response.body()?.followerBio!!
//                } else {
//                    //
//                }
//            }
//
//            override fun onFailure(call: retrofit2.Call<FollowerResponseDataBio>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//        })
//        return follower
//    }
}

