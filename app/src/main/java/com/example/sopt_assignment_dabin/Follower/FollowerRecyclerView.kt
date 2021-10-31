package com.example.sopt_assignment_dabin


import HorizontalItemDecorator
import android.content.Intent
import android.icu.text.IDNA
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sopt_assignment_dabin.Follower.DetailActivity
import com.example.sopt_assignment_dabin.Follower.FollowerListData
import com.example.sopt_assignment_dabin.databinding.FragmentFollowerRecyclerViewBinding
import com.example.sopt_assignment_dabin.Follower.FollowerRecyclerViewAdapter

class FollowerRecyclerView : Fragment() {
    private lateinit var followerAdapter: FollowerRecyclerViewAdapter
    private var _binding: FragmentFollowerRecyclerViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerRecyclerViewBinding.inflate(layoutInflater, container, false)
        initFollowerAdapter()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun initFollowerAdapter() {
        binding.container.addItemDecoration(HorizontalItemDecorator(requireActivity(), R.drawable.recyclerview_line_divider, 0, 0, 0))
        followerAdapter = FollowerRecyclerViewAdapter()
        binding.container.adapter = followerAdapter

        val follower1 = "https://images.chosun.com/resizer/Uq7kIYPcL-jTdlvCsPtXZGS7bx0=/568x313/smart/cloudfront-ap-northeast-1.images.arcpublishing.com/chosun/P7B24KRS2XSGACGGMJLJDDQIHE.jpg"
        val follower2 = "https://news.nateimg.co.kr/orgImg/hi/2021/09/06/d2c70b2c-27db-4073-97c6-7956d353918b.jpg"
        val follower3 = "https://cdn.entermedia.co.kr/news/photo/202108/27226_50216_3140.jpg"
        val follower4 = "https://images.chosun.com/resizer/Uq7kIYPcL-jTdlvCsPtXZGS7bx0=/568x313/smart/cloudfront-ap-northeast-1.images.arcpublishing.com/chosun/P7B24KRS2XSGACGGMJLJDDQIHE.jpg"
        val follower5 = "https://news.nateimg.co.kr/orgImg/hi/2021/09/06/d2c70b2c-27db-4073-97c6-7956d353918b.jpg"
        val follower6 = "https://cdn.entermedia.co.kr/news/photo/202108/27226_50216_3140.jpg"
        followerAdapter.followerList.addAll(
            listOf(
                FollowerListData("몬익화", "반갑습니다", follower1),
                FollowerListData("리정", "오하이오", follower2),
                FollowerListData("아이키", "111111하이", follower3),
                FollowerListData("박지성", "22222하이", follower4),
                FollowerListData("김다빈", "3333하이", follower5),
                FollowerListData("김다빈", "4444하이", follower6),
                FollowerListData("박지성", "22222하이", follower4),
                FollowerListData("김다빈", "3333하이", follower5),
                FollowerListData("김다빈", "4444하이", follower6)
            )
        )
        followerAdapter.notifyDataSetChanged()
    }
}

