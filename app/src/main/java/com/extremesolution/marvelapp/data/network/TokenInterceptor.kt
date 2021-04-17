package com.extremesolution.marvelapp.data.network

import android.util.Log
import com.extremesolution.marvelapp.data.security.Hashing
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(
    private val publicKey: String,
    private val privateKey: String,
    private val ts: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val hash = Hashing.md5(ts + privateKey + publicKey)
        val url = original.url.newBuilder()
            .addQueryParameter("apikey", publicKey)
            .addQueryParameter("ts", ts)
            .addQueryParameter("hash", hash).build()
        Log.e("URL", "?apikey=" + publicKey + "&ts=" + ts + "&hash=" + hash)
        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }


}