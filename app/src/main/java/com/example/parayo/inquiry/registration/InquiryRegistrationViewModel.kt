package com.example.parayo.inquiry.registration

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.parayo.api.ParayoApi
import com.example.parayo.api.request.InquiryRequest
import com.example.parayo.api.response.ApiResponse
import net.codephobia.ankomvvm.lifecycle.BaseViewModel
import retrofit2.Response

class InquiryRegistrationViewModel(app: Application) : BaseViewModel(app) {

    var productId = -1L
    var inquiryId: Long = -1L
    var inquiryType: String? = null

    val content = MutableLiveData("")

    suspend fun register() {
        val response = registerInquiry()
        if (!response.success) {
            toast(response.message ?: "알 수 없는 오류가 발생했습니다.")
        } else {
            toast("등록되었습니다.")
            finishActivity(RESULT_CODE_REGISTER_INQUIRY) {}
        }
    }

    private suspend fun registerInquiry() = try {
        val request = validateParamsAndMakeRequest()

        if(request.isContentEmpty) ApiResponse.error("내용을 입력해주세요.")
        else ParayoApi.instance.registerInquiry(request)
    } catch (e: Exception) {
        ApiResponse.error<Response<Void>>("알 수 없는 오류가 발생했습니다.")
    }

    private fun validateParamsAndMakeRequest(): InquiryRequest {
        val type = inquiryType
            ?: throw IllegalStateException("inquiryType이 null.")

        val pid = productId.let {
            if (it == -1L) throw IllegalStateException("잘못된 productId.")
            else it
        }

        val questionId = if (inquiryId == -1L) null else inquiryId

        return InquiryRequest(type, questionId, pid, content.value)
    }

    companion object {
        const val RESULT_CODE_REGISTER_INQUIRY = 1
    }

}