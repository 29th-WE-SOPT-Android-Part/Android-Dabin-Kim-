package com.example.sopt_assignment_dabin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sopt_assignment_dabin.databinding.ActivityPreferenceBinding

class PreferenceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPreferenceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        autoLogin()
    }

    private fun autoLogin() {
        binding.ctAutoLogin.setOnClickListener {
            binding.ctAutoLogin.toggle()
            AutoLogin.setAutoLogin(this, !binding.ctAutoLogin.isChecked)
            AutoLogin.removeAutoLogin(this)
        }
    }
}