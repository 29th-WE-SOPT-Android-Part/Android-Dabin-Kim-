package com.example.sopt_assignment_dabin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.sopt_assignment_dabin.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity(){
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


       var name = intent.getStringExtra("name").toString()
        binding.tvMyname.text = name


    }
}