package com.extremesolution.marvelapp.general.changeLanguage

import android.content.Context
import com.extremesolution.marvelapp.data.storage.UserPreferences

object Language {
    var selectedLanguage: String? = null
    var value: String? = null


    fun getLanguage(context: Context): String? {
        val userPreferences = UserPreferences(context)
        try {
            selectedLanguage = userPreferences.appLanguage
        } catch (e: java.lang.Exception) {
            selectedLanguage = AppConstants.Arabic
            e.printStackTrace()
        }
        return if (selectedLanguage.equals("English", ignoreCase = true)
        ) {
            "en"
        } else {
            "ar"
        }
    }

}
