package com.extremesolution.marvelapp.data.network

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.extremesolution.marvelapp.ui.base.BaseFragment
import com.google.android.material.snackbar.Snackbar

fun <A : Activity> Activity.startNewActivity(activity: Class<A>) {
    Intent(this, activity).also {
        it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(it)
    }
}

fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.enable(enabled: Boolean) {
    isEnabled = enabled
    alpha = if (enabled) 1f else 0.5f
}

fun View.snackbar(message: String, action: (() -> Unit)? = null, activity: FragmentActivity) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_INDEFINITE)

    if (errorCookieBar[activity] == null) {
        errorCookieBar[activity] = snackbar
        action?.let {
            snackbar.setAction("Retry") {
                errorCookieBar.remove(activity)
                it()
            }
        }
        snackbar.show()
    }
}

//Adding the fragment that has connection error to check if this fragment called again or not
val errorCookieBar = HashMap<FragmentActivity, Snackbar>()

fun Fragment.handleApiError(failure: Resource.Failure, retry: (() -> Unit)? = null) {

    var fragmentActivity =
        activity?.let {
            it
        }
    when {
        failure.isNetworkError -> requireView().snackbar(
            "Please check your internet connection",
            retry, fragmentActivity!!
        )

        failure.isNetworkError -> {
            activity?.let {
                requireView().snackbar(
                    "Please check your internet connection",
                    retry, it
                )
            }
        }

        failure.errorCode == 401 -> {
            (this as BaseFragment<*, *, *>).logout()
        }
        else -> {
            val error = failure.errorBody?.string().toString()
            activity?.let {
                requireView().snackbar(error, retry, it)
            }
        }
    }
}


