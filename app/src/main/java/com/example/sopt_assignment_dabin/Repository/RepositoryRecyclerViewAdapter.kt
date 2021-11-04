package com.example.sopt_assignment_dabin.Repository

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sopt_assignment_dabin.ItemTouchHelperListener
import com.example.sopt_assignment_dabin.databinding.ItemRepositoryListBinding

class RepositoryRecyclerViewAdapter() : RecyclerView.Adapter<RepositoryRecyclerViewAdapter.RepositoryViewHolder>(), ItemTouchHelperListener {
    val repositoryList = mutableListOf<RepositoryListData>()

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        repositoryList.removeAt(fromPosition)
        repositoryList.add(toPosition, repositoryList[fromPosition])
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemSwipe(position: Int) {
        repositoryList.removeAt(position)
        notifyItemRemoved(position)
    }

    class RepositoryViewHolder(private val view: ItemRepositoryListBinding) : RecyclerView.ViewHolder(view.root) {
        fun onBind(data: RepositoryListData) {
            view.tvName.text = data.name
            view.tvStory.text = data.story
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
}
