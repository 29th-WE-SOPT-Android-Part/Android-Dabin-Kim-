package com.example.sopt_assignment_dabin.repository

import androidx.recyclerview.widget.DiffUtil
import com.example.sopt_assignment_dabin.data.local.RepositoryResponseData

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