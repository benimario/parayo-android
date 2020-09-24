package com.example.parayo.api

import android.content.Intent
import com.example.parayo.App
import com.example.parayo.common.Prefs
import com.example.parayo.signin.SigninActivity
import okhttp3.Interceptor
import okhttp3.Response
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.intentFor

class TokenRefreshInterceptor : Interceptor, AnkoLogger {
    override fun intercept(chain: Interceptor.Chain): Response {
        debug("토큰 갱신 요청")
        val original = chain.request()
        val request = original.newBuilder().apply {
            Prefs.refreshToken?.let { header("Authorization", it) }
            method(original.method(), original.body())
        }.build()

        val response = chain.proceed(request)

        if(response.code() == 401) {
            App.instance.run {
                val intent = intentFor<SigninActivity>().apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                startActivity(intent)
            }
        }

        return response
    }
}