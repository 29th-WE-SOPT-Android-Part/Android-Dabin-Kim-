package com.example.sopt_assignment_dabin.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sopt_assignment_dabin.R
import com.example.sopt_assignment_dabin.data.local.Room.RoomHelper
import com.example.sopt_assignment_dabin.databinding.ActivityPreferenceBinding
import com.example.sopt_assignment_dabin.util.MyUtil.getHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

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
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    getHelper().updateLogin(false)
                } catch (e: Exception) {
                    e.toString()
                }
            }
        }
    }
}