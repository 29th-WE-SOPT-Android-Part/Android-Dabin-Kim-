package com.example.sopt_assignment_dabin.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.sopt_assignment_dabin.R
import com.example.sopt_assignment_dabin.data.local.Room.RoomDAO
import com.example.sopt_assignment_dabin.data.local.Room.RoomHelper
import com.example.sopt_assignment_dabin.databinding.ActivityOnBoardingBinding
import com.example.sopt_assignment_dabin.util.MyUtil.getHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //앱을 처음 실행하는 경우가 아니면 온보딩 화면 안뜨게 함
        CoroutineScope(Dispatchers.IO).launch {
            try {
                if (getHelper().get().onBording == false) {
                    send()
                }
            } catch (e: Exception) {
                e.toString()
            }
        }

        //Toolbar연결
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fc_container) as NavHostFragment
        navController = navHostFragment.findNavController()

        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    //로그인 화면으로 이동
    private fun send() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
        finish()
    }
}