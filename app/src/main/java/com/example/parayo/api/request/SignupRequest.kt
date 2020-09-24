package com.example.parayo.api.request

import android.util.Patterns
import com.example.parayo.common.Prefs

class SignupRequest(
    val email: String?,
    val password: String?,
    val name: String?,
    val fcmToken: String? = Prefs.fcmToken
) {

    fun isNotValidEmail() =
        email.isNullOrBlank()
                || !Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun isNotValidPassword() =
        password.isNullOrBlank() || password.length !in 8..20

    fun isNotValidName() =
        name.isNullOrBlank() || name.length !in 2..20

}