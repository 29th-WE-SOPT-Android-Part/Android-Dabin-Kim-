package com.example.sopt_assignment_dabin.Follower

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.sopt_assignment_dabin.R
import com.example.sopt_assignment_dabin.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        var name = intent.getStringExtra("name")
        var image = intent.getStringExtra("image")
        binding.tvMyname.text = name
        Glide.with(this).load(image).into(binding.ivJjangu)
        setContentView(binding.root)
    }
}