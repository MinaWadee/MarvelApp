package com.extremesolution.marvelapp.data.network

import android.content.Context
import com.extremesolution.marvelapp.general.changeLanguage.Language
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.extremesolution.marvelapp.BuildConfig

class ApiClient {
    companion object {
        private const val BASE_URL = "http://gateway.marvel.com/v1/public/"
        private const val PUBLIC_KEY = "9333873d373415a7c79a9a9a7064c036"
        private const val PRIVATE_KEY = "63462df3789dfd735cb8390ffab4e1cbea16707b"

    }

    fun <Api> buildApi(api: Class<Api>/*, authToken: String? = null, context: Context? = null*/): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(TokenInterceptor(PUBLIC_KEY,PRIVATE_KEY, System.currentTimeMillis().toString())).also { client ->
                        if (BuildConfig.DEBUG) {
                            val logging = HttpLoggingInterceptor()
                            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                            client.addInterceptor(logging)
                        }
                    }.build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }

}