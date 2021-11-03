package com.example.sopt_assignment_dabin.ViewPagerFragment

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.sopt_assignment_dabin.databinding.FragmentCameraBackgroundViewpagerBinding


class CameraFragment : Fragment() {
    private var _binding: FragmentCameraBackgroundViewpagerBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCameraBackgroundViewpagerBinding.inflate(layoutInflater, container, false)

        clickGallery()
        return binding.root
    }

    override fun onDestroy() {
        super<Fragment>.onDestroy()
        _binding = null
    }

    private fun checkPermission() {
        val cameraPermission = ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (cameraPermission == PackageManager.PERMISSION_GRANTED) {
            //프로그램 진행
            startProcess()
        } else {
            //권한요청
            requestPermission()
        }
    }

    private fun startProcess() {
        val intent = Intent()
        intent.setType("image/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        getResultText.launch(intent)
    }

    var getResultText = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            val uri = intent?.data
            Glide.with(this).load(uri).into(binding.ivCamera)
        }
        //else if (result.resultCode == Activity.RESULT_CANCELED) {} =>Activity.RESULT_CANCELED일때 처리코드가 필요하다면
    }

    private fun requestPermission() {
        permissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    private val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
        when (isGranted) {
            true -> startProcess()
            false -> Toast.makeText(getActivity(), "갤러리 권한을 허용해주세요.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clickGallery(){
        binding.bvCamera.setOnClickListener {
            checkPermission()
        }
    }
}
