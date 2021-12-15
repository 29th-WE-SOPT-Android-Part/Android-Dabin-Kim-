package com.example.sopt_assignment_dabin.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.sopt_assignment_dabin.R
import com.example.sopt_assignment_dabin.sopt.SignServiceCreator
import com.example.sopt_assignment_dabin.data.local.SignResponseWrapperData
import com.example.sopt_assignment_dabin.data.local.SigninRequestData
import com.example.sopt_assignment_dabin.data.local.SigninResponseData
import com.example.sopt_assignment_dabin.databinding.ActivitySignInBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {
    private lateinit var getResultText: ActivityResultLauncher<Intent>//회원가입 데이터 리턴받을 때 사용
    private lateinit var binding: ActivitySignInBinding //해당 class에서만 사용되니 private으로 선언하는 습관

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAnimation(this)

        //모든 데이터가 입력되었을때 로그인 버튼 색깔 바뀌게하기->addTextChangedListener 사용
        //인터페이스인 TextWatcher의 추상메서드인 3개함수 모두 오버라이드
        binding.etPassIn.addTextChangedListener(object : TextWatcher {
            @SuppressLint("ResourceAsColor")
            override fun afterTextChanged(inputChar: Editable?) {
            }

            override fun beforeTextChanged(inputChar: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(inputChar: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (isEtIdEmpty() != true && isEtPassword() != true && inputChar.toString() != "") {
                    binding.bvLogin.setBackgroundResource(R.drawable.rectangle_sopt_radius_8)
                }
            }
        })

        //로그인 버튼 눌렀을때 데이터 입력에 따른 분기 이벤트
        binding.bvLogin.setOnClickListener {
            clickLogin()
        }

        //회원가입 버튼시 이벤트->데이터 리턴받아와야함!
        binding.tvSignUp.setOnClickListener {
            clickSignUp()
        }

        //회원가입 데이터 받아오기
        getResultText = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val id = result.data?.getStringExtra("id")
                val pass = result.data?.getStringExtra("pass")
                binding.etIdIn.setText(id)
                binding.etPassIn.setText(pass)
            }
        }
    }

    //fade in&fade out 애니메이션
    private fun setAnimation(activity: SignInActivity) {
        //apply로 묶어주었으면 binding.tvTitle~ 에서 binding생략 가능
        binding.apply {
            val animation = AnimationUtils.loadAnimation(activity, R.anim.fade_in)
            val animation2 = AnimationUtils.loadAnimation(activity, R.anim.fade_out)
            ivSigninGithub.startAnimation(animation2)
            ivSigninGithub.startAnimation(animation)
        }
    }

    //모든 데이터가 입력되었는지 확인
    private fun isEtIdEmpty(): Boolean {
        return binding.etIdIn.text.isNullOrEmpty()
    }

    private fun isEtPassword(): Boolean {
        return binding.etPassIn.text.isNullOrEmpty()
    }

    private fun isAllEditTextEmpty(): Boolean {
        return isEtIdEmpty() || isEtPassword()
    }

    private fun clickLogin() {
        if (isAllEditTextEmpty()) {
            Toast.makeText(this, "모든 정보를 입력해주세요", Toast.LENGTH_SHORT).show()
        } else {
            initNetwork()
        }
    }

    private fun clickSignUp() {
        val intent = Intent(this, SignUpActivity::class.java)
        getResultText.launch(intent)
    }

    private fun initNetwork() {
        val requestData = SigninRequestData(
            email = binding.etIdIn.text.toString(),
            password = binding.etPassIn.text.toString()
        )
        val call: Call<SignResponseWrapperData<SigninResponseData>> = SignServiceCreator.signinService.signinLogin(requestData)
        call.enqueue(object : Callback<SignResponseWrapperData<SigninResponseData>> {
            override fun onResponse(call: Call<SignResponseWrapperData<SigninResponseData>>, response: Response<SignResponseWrapperData<SigninResponseData>>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@SignInActivity, "${binding.etIdIn.text}님 환영합니다", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@SignInActivity, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@SignInActivity, "등록되지 않은 사용자입니다", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SignResponseWrapperData<SigninResponseData>>, t: Throwable) {
                Log.d("Network", "error:$t")
            }
        })

    }
}