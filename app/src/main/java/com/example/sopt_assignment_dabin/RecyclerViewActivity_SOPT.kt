package com.example.sopt_assignment_dabin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.sopt_assignment_dabin.databinding.ActivityRecyclerViewBinding
import com.example.sopt_assignment_dabin.databinding.ItemSampleListBinding

class RecyclerView_Activity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerViewBinding
    private lateinit var myAdapter: RecyclerViewActivityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()

    }


    private fun initAdapter() {

        myAdapter = RecyclerViewActivityAdapter()
        binding.container.adapter = myAdapter
        myAdapter.list.addAll(
            listOf(
                Introduce_SOPT("김다빈", "하이"),
                Introduce_SOPT("아아아", "하이"),
                Introduce_SOPT("사사사", "하이"),
                Introduce_SOPT("김다빈", "하이"),
                Introduce_SOPT("아아아", "하이"),
                Introduce_SOPT("사사사", "하이")
            )
        )
        myAdapter.notifyDataSetChanged()
    }
}


class RecyclerViewActivityAdapter(
) : RecyclerView.Adapter<RecyclerViewActivityAdapter.ViewHolder>() {
    var list = mutableListOf<Introduce_SOPT>()

    class ViewHolder(private val view: ItemSampleListBinding) : RecyclerView.ViewHolder(view.root) {
        fun onBind(data: Introduce_SOPT) {
            view.tvName.text = data.name
            view.tvStory.text = data.story
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemSampleListBinding.inflate(LayoutInflater.from(parent.context))
        //  val view=inflater.inflate(R.layout.item_sample_list,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount() = list.size //한줄로 리턴되는 함수 가독성~

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(list[position])
    }

}