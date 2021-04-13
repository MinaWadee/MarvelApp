package com.extremesolution.marvelapp.general.changeLanguage

import android.content.Context

object PrefUtils {
    fun setLanguage(currentLang: LanguageType, ctx: Context?) {
        val complexPreferences: ComplexPreferences =
            ComplexPreferences.getComplexPreferences(ctx!!, "user_pref", Context.MODE_PRIVATE)
        complexPreferences.putObject("user_pref_value", currentLang)
        complexPreferences.commit()
    }

    fun getLanguage(ctx: Context): LanguageType {
        val complexPreferences: ComplexPreferences =
            ComplexPreferences.getComplexPreferences(ctx, "user_pref", Context.MODE_PRIVATE)
        return complexPreferences.getObject("user_pref_value", LanguageType::class.java)!!
    }
}
