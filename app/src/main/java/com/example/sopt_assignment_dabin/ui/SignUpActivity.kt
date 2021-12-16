package com.example.sopt_assignment_dabin.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sopt_assignment_dabin.R
import com.example.sopt_assignment_dabin.data.local.SiginupResponseData
import com.example.sopt_assignment_dabin.sopt.SignServiceCreator
import com.example.sopt_assignment_dabin.data.local.SignResponseWrapperData
import com.example.sopt_assignment_dabin.data.local.SignupRequestData
import com.example.sopt_assignment_dabin.databinding.ActivitySignUpBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //모든 데이터가 입력되었을때 로그인 버튼 색깔 바뀌게하기->addTextChangedListener 사용
        binding.etPass.addTextChangedListener(object : TextWatcher {
            @SuppressLint("ResourceAsColor")
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.etPass.text.isNotBlank() && binding.etName.text.isNotBlank() && binding.etId.text.isNotBlank()) {
                    binding.bvLogin.setBackgroundResource(R.drawable.rectangle_sopt_radius_8)
                }
            }
        })

        //회원가입 완료 버튼 분기 이벤트
        binding.bvLogin.setOnClickListener {
            clickLogin()
        }
    }

    private fun clickLogin() {
        if (binding.etPass.text.isNotBlank() && binding.etName.text.isNotBlank() && binding.etId.text.isNotBlank()) {
            initNetwork()
        } else {
            Toast.makeText(this, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
        }
    }

    //Level1->SOPT 네트워크 통신시
    private fun initNetwork() {
        val requestSignupData = SignupRequestData(
            name = binding.etName.text.toString(),
            email = binding.etId.text.toString(),
            password = binding.etPass.text.toString()
        )

        CoroutineScope(Dispatchers.IO).launch {
            try {
                SignServiceCreator.signupService.signupLogin(requestSignupData)
                val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
                    .putExtra("id", binding.etId.text.toString())
                    .putExtra("pass", binding.etPass.text.toString())
                setResult(Activity.RESULT_OK, intent)
                finish() //화면이동시 intent아닌 finish로 스택에서 제거
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@SignUpActivity, "이미 등록된 사용자입니다", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

