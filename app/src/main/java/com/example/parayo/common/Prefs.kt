package com.example.parayo.common

import android.preference.PreferenceManager
import com.example.parayo.App

object Prefs {

    private const val TOKEN = "token"
    private const val REFRESH_TOKEN = "refresh_token"
    private const val USER_NAME = "user_name"
    private const val USER_ID = "user_id"
    private const val FCM_TOKEN = "fcm_token"
    private const val NOTIFICATION_ID = "notification_id"

    val prefs by lazy {
        PreferenceManager
            .getDefaultSharedPreferences(App.instance)
    }

    var token
        get() = prefs.getString(TOKEN, null)
        set(value) = prefs.edit()
            .putString(TOKEN, value)
            .apply()

    var refreshToken
        get() = prefs.getString(REFRESH_TOKEN, null)
        set(value) = prefs.edit()
            .putString(REFRESH_TOKEN, value)
            .apply()

    var userName
        get() = prefs.getString(USER_NAME, null)
        set(value) = prefs.edit()
            .putString(USER_NAME, value)
            .apply()

    var userId
        get() = prefs.getLong(USER_ID, 0)
        set(value) = prefs.edit()
            .putLong(USER_ID, value)
            .apply()

    var fcmToken
        get() = prefs.getString(FCM_TOKEN, null)
        set(value) = prefs.edit()
            .putString(FCM_TOKEN, value)
            .apply()

    var notificationId
        get() = prefs.getInt(NOTIFICATION_ID, 0)
        set(value) = prefs.edit()
            .putInt(NOTIFICATION_ID, value)
            .apply()

}