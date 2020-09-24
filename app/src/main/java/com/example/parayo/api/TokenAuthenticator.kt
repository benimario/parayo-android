package com.example.parayo.api

import com.example.parayo.api.response.ApiResponse
import com.example.parayo.common.Prefs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.error

class TokenAuthenticator : Authenticator, AnkoLogger {
    override fun authenticate(
        route: Route?,
        response: Response
    ): Request? {
        if (response.code() == 401) {
            debug("토큰 갱신 필요")
            return runBlocking {
                val tokenResponse = refreshToken()

                if (tokenResponse.success) {
                    debug("토큰 갱신 성공")
                    Prefs.token = tokenResponse.data
                } else {
                    error("토큰 갱신 실패.")
                    Prefs.token = null
                    Prefs.refreshToken = null
                }

                Prefs.token?.let { token ->
                    debug("토큰 = $token")
                    response.request()
                        .newBuilder()
                        .header("Authorization", token)
                        .build()
                }
            }
        }

        return null
    }

    private suspend fun refreshToken() =
        withContext(Dispatchers.IO) {
            try {
                ParayoRefreshApi.instance.refreshToken()
            } catch (e: Exception) {
                ApiResponse.error<String>("인증 실패")
            }
        }

}