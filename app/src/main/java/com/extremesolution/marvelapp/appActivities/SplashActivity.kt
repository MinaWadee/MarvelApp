package com.extremesolution.marvelapp.appActivities

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.extremesolution.marvelapp.R
import com.extremesolution.marvelapp.data.network.startNewActivity
import jp.wasabeef.glide.transformations.BlurTransformation


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splach_activity)

        val imageView = findViewById<ImageView>(R.id.customProfileGridImg)

        //To make bg image blur
        Glide.with(this).load(R.drawable.mcu_background)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 3)))
            .into(imageView)

        //Delay transaction
        Handler(Looper.getMainLooper()).postDelayed({
            callNextActivity()
        }, 2000)

    }

    private fun callNextActivity() {
        val activity = MainActivity::class.java
        startNewActivity(activity)
    }

}