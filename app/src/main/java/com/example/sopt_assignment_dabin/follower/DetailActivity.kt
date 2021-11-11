package com.example.sopt_assignment_dabin.follower

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.sopt_assignment_dabin.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val image = intent.getStringExtra("image")
        val bio = intent.getStringExtra("bio")
        binding.tvMyname.text = name
        binding.tvMyintroduce.text=bio
        Glide.with(this).load(image).into(binding.ivJjangu)
    }
}