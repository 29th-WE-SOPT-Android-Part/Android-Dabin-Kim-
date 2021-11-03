package com.example.sopt_assignment_dabin

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class DatabindingImage(val profile: String) {

    object MyBind {
        @JvmStatic
        @BindingAdapter("setImage")
        fun setImageUrl(view: ImageView, profile: String) {

            Glide.with(view.context)
                .load(profile)
                .into(view)

        }

    }

}