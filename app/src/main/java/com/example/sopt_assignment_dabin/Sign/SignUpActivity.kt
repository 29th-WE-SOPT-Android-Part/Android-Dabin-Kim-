package com.example.sopt_assignment_dabin.Sign

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
import com.example.sopt_assignment_dabin.SOPTNetwork.SiginupResponseData
import com.example.sopt_assignment_dabin.SOPTNetwork.SignServiceCreator
import com.example.sopt_assignment_dabin.Sign.data.SignResponseWrapperData
import com.example.sopt_assignment_dabin.Sign.data.SignupRequestData
import com.example.sopt_assignment_dabin.databinding.ActivitySignUpBinding
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
                if (isEtNameEmpty() != true && isEtIdEmpty() != true && isEtPassword() != true && p0.toString() != "") {
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
        if (isAllEditTextEmpty()) {
            Toast.makeText(this, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
        } else {
            initNetwork()
        }
    }

    //Level1->SOPT 네트워크 통신시
    private fun initNetwork() {
        val requestSignupData = SignupRequestData(
            name = binding.etName.text.toString(),
            email = binding.etId.text.toString(),
            password = binding.etPass.text.toString()
        )
        val call: Call<SignResponseWrapperData<SiginupResponseData>> = SignServiceCreator.signupService.signupLogin(requestSignupData)

        call.enqueue(object : Callback<SignResponseWrapperData<SiginupResponseData>> {
            override fun onResponse(call: Call<SignResponseWrapperData<SiginupResponseData>>, response: Response<SignResponseWrapperData<SiginupResponseData>>) {
                if (response.isSuccessful) {
                    val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
                        .putExtra("id", binding.etId.text.toString())
                        .putExtra("pass", binding.etPass.text.toString())
                    setResult(Activity.RESULT_OK, intent)
                    finish() //화면이동시 intent아닌 finish로 스택에서 제거
                } else {
                    Toast.makeText(this@SignUpActivity, "이미 등록된 사용자입니다", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SignResponseWrapperData<SiginupResponseData>>, t: Throwable) {
                Log.d("Network", "error:$t")
            }
        })
    }


    private fun isEtNameEmpty(): Boolean {
        return binding.etName.text.isNullOrEmpty()
    }

    private fun isEtIdEmpty(): Boolean {
        return binding.etId.text.isNullOrEmpty()
    }

    private fun isEtPassword(): Boolean {
        return binding.etPass.text.isNullOrEmpty()
    }

    private fun isAllEditTextEmpty(): Boolean {
        return isEtNameEmpty() || isEtIdEmpty() || isEtPassword()
    }
}

