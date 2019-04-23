package com.celaloglu.zafer.weather.util

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

fun <T> T.toDeferred() = GlobalScope.async { this@toDeferred }

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}
