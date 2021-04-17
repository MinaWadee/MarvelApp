package com.extremesolution.marvelapp.appActivities

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.annotation.AnimatorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.extremesolution.marvelapp.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // In Activity's onCreate() for instance
        val w: Window = window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

    fun replaceFragment(
        fragment: Fragment, containerId: Int,
        needToAddToBackStack: Boolean = false,
        sendingData: Boolean = false,
        bundle: Bundle? = null,
        @AnimatorRes inAnimRes: Int = 0,
        @AnimatorRes outAnimRes: Int = 0
    ) {
        val name = fragment.javaClass.simpleName
            if (sendingData) fragment.arguments = bundle
        supportFragmentManager.beginTransaction().apply {
            if (inAnimRes != 0 || outAnimRes != 0) setCustomAnimations(inAnimRes, outAnimRes)
            replace(containerId, fragment, name)
            if (needToAddToBackStack) addToBackStack(name)
        }.commit()
    }


}