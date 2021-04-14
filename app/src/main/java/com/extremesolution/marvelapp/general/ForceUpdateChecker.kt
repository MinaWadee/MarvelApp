package com.extremesolution.marvelapp.general

import android.content.Context
import android.content.pm.PackageManager
import android.text.TextUtils
import android.util.Log
import com.google.firebase.remoteconfig.FirebaseRemoteConfig

class ForceUpdateChecker(
    private val context: Context,
    private val onUpdateNeededListener: OnUpdateNeededListener?
) {
    fun check() {
        val remoteConfig: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

        /* if (remoteConfig.getBoolean(KEY_UPDATE_REQUIRED)) {*/currentVersionMinor =
            remoteConfig.getString(KEY_CURRENT_VERSION_MINOR)
        currentVersionMajor =
            remoteConfig.getString(KEY_CURRENT_VERSION_MAJOR)
        val appVersion = getAppVersion(context)
        val updateUrl: String =
            remoteConfig.getString(KEY_UPDATE_URL)
        if (!TextUtils.equals(
                currentVersionMinor,
                appVersion
            ) && onUpdateNeededListener != null
        ) {
            onUpdateNeededListener.onUpdateNeeded(updateUrl)
        }
        /*}*/
    }

    private fun getAppVersion(context: Context): String {
        var result = ""
        try {
            result = context.packageManager
                .getPackageInfo(context.packageName, 0).versionName
            result = result.replace("[a-zA-Z]|-".toRegex(), "")
        } catch (e: PackageManager.NameNotFoundException) {
            Log.e(TAG, e.message!!)
        }
        return result
    }

    interface OnUpdateNeededListener {
        fun onUpdateNeeded(updateUrl: String?)
    }

    class Builder(private val context: Context) {
        private var onUpdateNeededListener: OnUpdateNeededListener? = null
        fun onUpdateNeeded(onUpdateNeededListener: OnUpdateNeededListener?): Builder {
            this.onUpdateNeededListener = onUpdateNeededListener
            return this
        }

        fun build(): ForceUpdateChecker {
            return ForceUpdateChecker(
                context,
                onUpdateNeededListener
            )
        }

        fun check(): ForceUpdateChecker {
            val forceUpdateChecker = build()
            forceUpdateChecker.check()
            return forceUpdateChecker
        }

    }

    companion object {
        const val KEY_UPDATE_REQUIRED = "force_update_required"
        const val KEY_CURRENT_VERSION_MINOR = "current_minor_version_ANDROID"
        const val KEY_CURRENT_VERSION_MAJOR = "current_major_version_ANDROID"
        const val KEY_UPDATE_URL = "force_update_store_url"
        private val TAG = ForceUpdateChecker::class.java.simpleName
        var currentVersionMinor: String? = null
        var currentVersionMajor: String? = null
        fun with(context: Context): Builder {
            return Builder(
                context
            )
        }
    }

}