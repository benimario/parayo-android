package com.example.parayo.signin

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.parayo.api.ParayoApi
import com.example.parayo.api.request.SigninRequest
import com.example.parayo.api.response.ApiResponse
import com.example.parayo.api.response.SigninResponse
import com.example.parayo.common.Prefs
import com.example.parayo.product.ProductMainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.codephobia.ankomvvm.lifecycle.BaseViewModel
import org.jetbrains.anko.error

class SigninViewModel(app: Application) : BaseViewModel(app) {

    val email = MutableLiveData("")
    val password = MutableLiveData("")

    suspend fun signin() {
        val request = SigninRequest(email.value, password.value)
        if(isNotValidSignin(request))
            return

        try {
            val response = requestSignin(request)
            onSigninResponse(response)
        } catch (e: Exception) {
            error("signin error", e)
            toast("알 수 없는 오류가 발생했습니다")
        }
    }

    private fun isNotValidSignin(request: SigninRequest) =
        when {
            request.isNotValidEmail() -> {
                toast("이메일 형식이 정확하지 않습니다.")
                true
            }
            request.isNotValidPassword() -> {
                toast("비밀번호는 8자 이상 20자 이하로 입력해주세요.")
                true
            }
            else -> false
        }

    private suspend fun requestSignin(request: SigninRequest) =
        withContext(Dispatchers.IO) {
            ParayoApi.instance.signin(request)
        }

    private fun onSigninResponse(response: ApiResponse<SigninResponse>) {
        if(response.success && response.data != null) {
            Prefs.token = response.data.token
            Prefs.refreshToken = response.data.refreshToken
            Prefs.userName = response.data.userName
            Prefs.userId = response.data.userId

            toast("로그인되었습니다.")
            startActivityAndFinish<ProductMainActivity>()
        } else {
            toast(response.message ?: "알 수 없는 오류가 발생했습니다.")
        }
    }

}
