package com.extremesolution.marvelapp.general.changeLanguage

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson


@Suppress("NAME_SHADOWING")
@SuppressLint("CommitPrefEdits")
class ComplexPreferences private constructor(context: Context, namePreferences: String, mode: Int) {

    private val context: Context
    private val preferences: SharedPreferences
    private val editor: SharedPreferences.Editor

    fun putObject(key: String?, `object`: Any?) {
        requireNotNull(`object`) { "object is null" }
        require(!(key == "" || key == null)) { "key is empty or null" }
        editor.putString(key, GSON.toJson(`object`))
    }

    fun commit() {
        editor.commit()
    }

    fun clearObject() {
        editor.clear()
    }

    fun <T> getObject(key: String, a: Class<T>?): T? {
        val lang = preferences.getString(key, null)

        return if (lang == null) {
          null
        } else {
            try {
                GSON.fromJson(lang, a)
            } catch (e: Exception) {
                throw IllegalArgumentException("Object storaged with key $key is instanceof other class")
            }
        }
    }

    companion object {
        private lateinit var complexPreferences: ComplexPreferences
        private val GSON = Gson()

        fun getComplexPreferences(context: Context, namePreferences: String, mode: Int): ComplexPreferences {
            complexPreferences = ComplexPreferences(context, namePreferences, mode)
            return complexPreferences
        }
    }

    init {
        var namePreferences: String? = namePreferences
        this.context = context

        if (namePreferences == null || namePreferences == "") {
            namePreferences = "complex_preferences"
        }

        preferences = context.getSharedPreferences(namePreferences, mode)
        editor = preferences.edit()
    }

}
