package com.extremesolution.marvelapp.data.security

import java.math.BigInteger
import java.security.MessageDigest
import kotlin.text.Charsets.UTF_8

object Hashing {
    fun md5(str: String): String = String.format("%032x",BigInteger(1,MessageDigest.getInstance("MD5").digest(str.toByteArray(UTF_8))))
    fun ByteArray.toHex() = joinToString("") { "%02x".format(it) }
}