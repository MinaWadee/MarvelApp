package com.extremesolution.marvelapp.data.storage

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences(context: Context) {

    private val applicationContext = context.applicationContext
    private val dataStore: DataStore<Preferences>

    init {
        dataStore = applicationContext.createDataStore(
            name = "my_data_store"
        )
    }

    val authToken: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[KEY_AUTH]
        }

    suspend fun saveAuthToken(authToken: String) {
        dataStore.edit { preferences ->
            preferences[KEY_AUTH] = authToken
        }
    }

    suspend fun clear() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
        val sharedPreferences =
            applicationContext.getSharedPreferences("UserObject", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }


    fun saveLang(SHARED_PREF_NAME: String, Lang: String){
        val sharedPreferences: SharedPreferences =
            applicationContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

        val editor = sharedPreferences.edit()
        editor.putString("Language",Lang)
        editor.apply()
    }

    val appLanguage:String
        get() {
            val sharedPreferences = applicationContext.getSharedPreferences("APP_LANGUAGE",Context.MODE_PRIVATE)
            return sharedPreferences.getString("Language","english")!!
        }

    companion object {
        private val KEY_AUTH = preferencesKey<String>("key_auth")
    }

}