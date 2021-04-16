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
        private const val BASE_URL = "http://gateway.marvel.com/"
    }

    fun <Api> buildApi(api: Class<Api>, authToken: String? = null, context: Context? = null): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        chain.proceed(chain.request().newBuilder().also {
                            it.addHeader("Authorization", "Bearer $authToken")
                            context?.let { it1 ->
                                Language.getLanguage(it1)?.let { locale ->
                                    it.addHeader("locale",
                                        locale
                                    )
                                }
                            }
                        }.build())
                    }.also { client ->
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