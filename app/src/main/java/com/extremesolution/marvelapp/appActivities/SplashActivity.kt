package com.extremesolution.marvelapp.appActivities

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.extremesolution.marvelapp.R
import com.extremesolution.marvelapp.data.network.startNewActivity
import com.extremesolution.marvelapp.data.storage.UserPreferences
import com.extremesolution.marvelapp.general.changeLanguage.AppConstants
import com.extremesolution.marvelapp.general.changeLanguage.Language
import com.extremesolution.marvelapp.general.changeLanguage.LanguageType
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.splach_activity.*
import java.util.*


@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {
    private var userPreferences: UserPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splach_activity)
        userPreferences = UserPreferences(this)
        setAppLanguage()

        //To make bg image blur
        Glide.with(this).load(R.drawable.mcu_background)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 3)))
            .into(customProfileGridImg)

        //Delay transaction
        Handler(Looper.getMainLooper()).postDelayed({
            callNextActivity()
        }, 2000)

    }

    private fun setAppLanguage() {
        val languageType = LanguageType()
        val config = Configuration()

        if (userPreferences?.appLanguage == "english") {
            languageType.languageType = AppConstants.English
            userPreferences?.saveLang("APP_LANGUAGE", languageType.languageType)
            config.locale = Locale.ENGLISH
            resources.updateConfiguration(config, resources.displayMetrics)
            Language.selectedLanguage = AppConstants.English
            val res = this.resources

            // Change locale settings in the app.
            val dm = res.displayMetrics
            val conf = res.configuration
            conf.locale = Locale.ENGLISH
            if (Build.VERSION.SDK_INT >= 25) {
                this.applicationContext.createConfigurationContext(conf)
                this.createConfigurationContext(conf)
            }
            res.updateConfiguration(conf, dm)
        } else {
            languageType.languageType = AppConstants.Arabic
            userPreferences?.saveLang("APP_LANGUAGE", languageType.languageType)
            val arLocale = Locale("ar")
            config.locale = arLocale
            resources.updateConfiguration(config, resources.displayMetrics)
            Language.selectedLanguage = AppConstants.Arabic
            val res = this.resources

            // Change locale settings in the app.
            val dm = res.displayMetrics
            val conf = res.configuration
            conf.locale = Locale("ar")
            if (Build.VERSION.SDK_INT >= 25) {
                this.applicationContext.createConfigurationContext(conf)
                this.createConfigurationContext(conf)

            }
            res.updateConfiguration(conf, dm)
        }
    }

    private fun callNextActivity() {
        val activity = MainActivity::class.java
        startNewActivity(activity)
    }

}