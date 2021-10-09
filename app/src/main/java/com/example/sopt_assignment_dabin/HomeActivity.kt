package com.example.sopt_assignment_dabin

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.animation.AnimationUtils
import com.bumptech.glide.Glide
import com.example.sopt_assignment_dabin.databinding.ActivityHomeMainBinding


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityHomeMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //애니메이션
        fun setAnimation() {
            val animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
            val animation2 = AnimationUtils.loadAnimation(this, R.anim.fade_out)
            binding.imageView.startAnimation(animation2)
            binding.tvTitleHome.startAnimation(animation2)
            binding.imageView.startAnimation(animation)
            binding.tvTitleHome.startAnimation(animation)
        }
        setAnimation()

        //gif삽입을 위해 Glide사용
        Glide.with(this).load(R.raw.jjangu).into(binding.imageView)

        //깃허브 페이지로 이동
        binding.ivGithubIcon.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/dabinKim-0318"))
            startActivity(intent)
        }


    }
}

