package com.example.sopt_assignment_dabin.Repository

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sopt_assignment_dabin.databinding.ItemRepositoryListBinding

class RepositoryAdapter(var repositoryList: List<RepositoryResponseData>) : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    class RepositoryViewHolder(private val view: ItemRepositoryListBinding) : RecyclerView.ViewHolder(view.root) {
        fun onBind(data: RepositoryResponseData) {
            view.tvName.text = data.repoName
            view.tvStory.text = data.reposUrl
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = ItemRepositoryListBinding.inflate(LayoutInflater.from(parent.context))
        return RepositoryViewHolder(view)
    }

    override fun getItemCount() = repositoryList.size

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.onBind(repositoryList[position])
    }

    fun setContact(contacts: List<RepositoryResponseData>) {
        val diffResult = DiffUtil.calculateDiff(ContactDiffUtil(this.repositoryList, repositoryList), false)
        diffResult.dispatchUpdatesTo(this)
        this.repositoryList = repositoryList
    }
}

