package com.example.sopt_assignment_dabin.Repository

import androidx.recyclerview.widget.DiffUtil

class ContactDiffUtil(private val oldList: List<RepositoryResponseData>, private val currentList: List<RepositoryResponseData>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = currentList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].repoName == currentList[newItemPosition].repoName
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == currentList[newItemPosition]
    }
}