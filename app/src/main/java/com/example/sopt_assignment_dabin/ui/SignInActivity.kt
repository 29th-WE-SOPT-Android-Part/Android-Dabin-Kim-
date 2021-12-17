package com.example.sopt_assignment_dabin.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.animation.AnimationUtils
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.sopt_assignment_dabin.R
import com.example.sopt_assignment_dabin.data.local.SigninRequestData
import com.example.sopt_assignment_dabin.databinding.ActivitySignInBinding
import com.example.sopt_assignment_dabin.sopt.SignServiceCreator
import com.example.sopt_assignment_dabin.util.MyUtil.getHelper
import com.example.sopt_assignment_dabin.util.MyUtil.shortToast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
                if (binding.etPassIn.text.isNotBlank() && binding.etIdIn.text.isNotBlank()) {
                    binding.bvLogin.setBackgroundResource(R.drawable.rectangle_sopt_radius_8)
                }
            }
        })

        //로그인 버튼 눌렀을때 데이터 입력에 따른 분기 이벤트
        binding.bvLogin.setOnClickListener {
            clickLogin()
        }

        //자동로그인
        autoClickEvent()
        isAutoLogin()

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

    private fun clickLogin() {
        if (binding.etPassIn.text.isNotBlank() && binding.etIdIn.text.isNotBlank()) {
            initNetwork()
        } else {
            shortToast("모든 정보를 입력해주세요")
        }
    }

    private fun clickSignUp() {
        val intent = Intent(this, SignUpActivity::class.java)
        getResultText.launch(intent)
    }

    //자동 로그인클릭 이벤트 발생
    private fun autoClickEvent() {
        binding.ctAutoLogin.setOnClickListener {
            binding.ctAutoLogin.toggle()
            setAutoLogin(binding.ctAutoLogin.isChecked, binding.etIdIn.text.toString(), binding.etPassIn.text.toString())
        }
    }

    //자동로그인 선택시 유저 정보 저장
    private fun setAutoLogin(autoLogin: Boolean, id: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                getHelper().updateInfo(autoLogin, id, password)
            } catch (e: Exception) {
                e.toString()
            }
        }
    }

    //일반 로그인 시 서버통신
    private fun initNetwork() {
        val requestData = SigninRequestData(
            email = binding.etIdIn.text.toString(),
            password = binding.etPassIn.text.toString()
        )
        CoroutineScope(Dispatchers.IO).launch {
            try {
                SignServiceCreator.signinService.signinLogin(requestData)
                withContext(Dispatchers.Main) {
                    shortToast("${binding.etIdIn.text}님 환영합니다")
                }
                val intent = Intent(this@SignInActivity, HomeActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    shortToast("등록되지 않은 사용자입니다")
                }
            }
        }
    }

    //자동 로그인 체크되어있을시 서버통신
    private fun isAutoLogin() {
        CoroutineScope(Dispatchers.IO).launch {
            if (getHelper().get().autoLogin) {
                val requestData = SigninRequestData(
                    email = getHelper().get().id,
                    password = getHelper().get().password
                )
                try {
                    SignServiceCreator.signinService.signinLogin(requestData)
                    withContext(Dispatchers.Main) {
                        shortToast("자동 로그인")
                    }
                    val intent = Intent(this@SignInActivity, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                } catch (e: Exception) {
                    e.toString()
                }
            }
        }
    }
}

/*  sharedpreference로 한거!
object AutoLogin {

    private const val STORAGE_KEY = "com.example.sopt_assignment_dabin.app"
    val ONBOARDING = "ONBOARDING"
    val AUTO_LOGIN = "AUTO_LOGIN"
    val USER_ID = "USER_ID"
    val USER_PASS = "USER_PASS"

    fun setOnBoarding(context: Context, init: Boolean) {
        getSharedpf(context).edit()
            .putBoolean(ONBOARDING, init)
            .apply()
    }

    fun getOnBoarding(context: Context): Boolean {
        return getSharedpf(context).getBoolean(ONBOARDING, true)
    }

    fun getAutoLogin(context: Context): Boolean {
        return getSharedpf(context).getBoolean(AUTO_LOGIN, false)
    }

    fun setAutoLogin(context: Context, checked: Boolean, id: String = "", pass: String = "") {
        getSharedpf(context).edit()
            .putBoolean(AUTO_LOGIN, checked)
            .putString(USER_ID, id)
            .putString(USER_PASS, pass)
            .apply()
    }

    fun removeAutoLogin(context: Context) {
        getSharedpf(context).edit()
            .remove(USER_ID)
            .remove(USER_PASS)
            .apply()
    }

    fun getSharedpf(context: Context): SharedPreferences {
        return context.getSharedPreferences(STORAGE_KEY, Context.MODE_PRIVATE)
    }
}*/
