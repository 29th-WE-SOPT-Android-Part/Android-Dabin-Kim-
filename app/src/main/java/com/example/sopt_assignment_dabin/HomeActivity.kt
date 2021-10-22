package com.example.sopt_assignment_dabin

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.sopt_assignment_dabin.databinding.ActivityHomeMainBinding


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeMainBinding.inflate(layoutInflater)

        setAnimation(this)
        //애니메이션

        //gif삽입을 위해 Glide사용
        Glide.with(this).load(R.raw.jjangu).into(binding.ivProfile)

        transactionEvent()
        setContentView(binding.root)

    }

    private fun transactionEvent() {
        val repositoryRecyclerViewFragment = RepositoryRecyclerView()
        val followerRecyclerViewFragment = FollowerRecyclerView()
        supportFragmentManager.beginTransaction().add(R.id.home_container, followerRecyclerViewFragment).commit()

        binding.bvFollower.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.home_container, followerRecyclerViewFragment).commit()
        }
        binding.bvRepository.setOnClickListener {
            supportFragmentManager.beginTransaction().replace(R.id.home_container, repositoryRecyclerViewFragment).commit()
        }
    }


    private fun setAnimation(activity: HomeActivity) {
        binding.apply {
            val animation = AnimationUtils.loadAnimation(activity, R.anim.fade_in)
            val animation2 = AnimationUtils.loadAnimation(activity, R.anim.fade_out)
            binding.ivProfile.startAnimation(animation2)
            binding.tvTitleHome.startAnimation(animation2)
            binding.ivProfile.startAnimation(animation)
            binding.tvTitleHome.startAnimation(animation)
        }
    }
}

