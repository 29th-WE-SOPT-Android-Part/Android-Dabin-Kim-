package com.example.sopt_assignment_dabin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sopt_assignment_dabin.databinding.ActivityRecyclerViewBinding
import com.example.sopt_assignment_dabin.databinding.ItemSampleListBinding

class RecyclerView_Activity : AppCompatActivity() {
    lateinit var binding:ActivityRecyclerViewBinding
     lateinit var adapter:RecyclerView_Activity_Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRecyclerViewBinding.inflate(layoutInflater)

        initAdapter()

        setContentView(binding.root)
    }


    private fun initAdapter() {
        val list = ArrayList<Introduce>()
        for (item in 0..10) {
            list.add(Introduce("item", "하이"))
             adapter = RecyclerView_Activity_Adapter(list)
            binding.container.adapter = adapter

            adapter.notifyDataSetChanged()

        }
    }
}


    class RecyclerView_Activity_Adapter(
        val list: ArrayList<Introduce>,
    ) :
        RecyclerView.Adapter<RecyclerView_Activity_Adapter.ViewHolder>() {


        class ViewHolder(private val view: ItemSampleListBinding) : RecyclerView.ViewHolder(view.root) {
            val name: TextView
            val story: TextView

            init {
                name = view.tvName
                story = view.tvStory
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = ItemSampleListBinding.inflate(LayoutInflater.from(parent.context))
            //  val view=inflater.inflate(R.layout.item_sample_list,parent,false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.name.setText(list.get(position).name)
            holder.story.setText(list.get(position).story)
        }

    }