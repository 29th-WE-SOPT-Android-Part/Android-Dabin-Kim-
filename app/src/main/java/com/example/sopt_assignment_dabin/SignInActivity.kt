package com.example.sopt_assignment_dabin

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.sopt_assignment_dabin.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var getResultText: ActivityResultLauncher<Intent>  //회원가입 데이터 리턴받을 때 사용
    private lateinit var binding: ActivitySignInBinding  //해당 class에서만 사용되니 private으로 선언하는 습관

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAnimation(this)

        //모든 데이터가 입력되었을때 로그인 버튼 색깔 바뀌게하기->addTextChangedListener 사용
        //TextWatcher의 추상메서드인 3개함수 모두 오버라이드
        binding.etPassIn.addTextChangedListener(object : TextWatcher {
            @SuppressLint("ResourceAsColor")
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (isEtIdEmpty() != true && isEtPassword() != true && p0.toString() != "") {
                    binding.bvLogin.setBackgroundResource(R.drawable.login_background2)
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
            if (result.resultCode == 1) {
                val id = result.data?.getStringExtra("id")
                val pass = result.data?.getStringExtra("pass")
                binding.etIdIn.setText(id)
                binding.etPassIn.setText(pass)
            } else {
                binding.etIdIn.text.clear()
                binding.etPassIn.text.clear()
            }
        }

    }

    //fade in&fade out 애니메이션
    private fun setAnimation(activity: SignInActivity) {
        binding.apply {
            val animation = AnimationUtils.loadAnimation(activity, R.anim.fade_in)
            val animation2 = AnimationUtils.loadAnimation(activity, R.anim.fade_out)
            binding.tvTitle.startAnimation(animation2)
            binding.tvTitle.startAnimation(animation)
        }
    }

    //모든 데이터가 입력되었는지 확인
    private  fun isEtIdEmpty(): Boolean {
        return binding.etIdIn.text.isNullOrEmpty()
    }

    private fun isEtPassword(): Boolean {
        return binding.etPassIn.text.isNullOrEmpty()
    }

    private fun isAllEditTextEmpty(): Boolean {
        return isEtIdEmpty() || isEtPassword()
    }

    private fun clickLogin() {
        if (isAllEditTextEmpty()) {  //(isAllEditTextEmpty() == true) 와 같은..!
            Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "${binding.etIdIn.text}님 환영합니다", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun clickSignUp() {
        val intent = Intent(this, SignUpActivity::class.java)
        getResultText.launch(intent)

    }

}

