package com.ubayadev.studentapp.view

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("android:imageUrl")
fun loadPhotoURL(imageView: ImageView, url:String?) {
    if(url != null) {
        val picasso = Picasso.Builder(imageView.context)
        picasso.listener { picasso, uri, exception ->
            exception.printStackTrace()
        }
        picasso.build().load(url).into(imageView)
        imageView.visibility = View.VISIBLE
    }
}
