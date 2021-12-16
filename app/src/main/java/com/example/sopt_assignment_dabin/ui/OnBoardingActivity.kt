package com.example.sopt_assignment_dabin.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.sopt_assignment_dabin.R
import com.example.sopt_assignment_dabin.databinding.ActivityOnBoardingBinding

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //앱을 처음 실행하는 경우가 아닌 경우 온보딩 화면 안뜨게 함
        if (AutoLogin.getOnBoarding(this) == false) {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}