package com.example.parayo.inquiry

import androidx.paging.PageKeyedDataSource
import com.example.parayo.App
import com.example.parayo.api.ParayoApi
import com.example.parayo.api.response.ApiResponse
import com.example.parayo.api.response.InquiryResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jetbrains.anko.toast

class InquiryDataSource(
    private val productId: Long? = null,
    private val requestUserId: Long? = null,
    private val productOwnerId: Long? = null
) : PageKeyedDataSource<Long, InquiryResponse>() {

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, InquiryResponse>
    ) {
        val response = getInquiries(Long.MAX_VALUE, NEXT)
        if (response.success) {
            response.data?.let {
                if (it.isNotEmpty())
                    callback.onResult(it, it.first().id, it.last().id)
            }
        } else {
            GlobalScope.launch(Dispatchers.Main) {
                showErrorMessage(response)
            }
        }
    }

    override fun loadAfter(
        params: LoadParams<Long>,
        callback: LoadCallback<Long, InquiryResponse>
    ) {
        val response = getInquiries(params.key, NEXT)
        if (response.success) {
            response.data?.let {
                if (it.isNotEmpty())
                    callback.onResult(it, it.last().id)
            }
        } else {
            GlobalScope.launch(Dispatchers.Main) {
                showErrorMessage(response)
            }
        }
    }

    override fun loadBefore(
        params: LoadParams<Long>,
        callback: LoadCallback<Long, InquiryResponse>
    ) {
        val response = getInquiries(params.key, PREV)
        if (response.success) {
            response.data?.let {
                if (it.isNotEmpty())
                    callback.onResult(it, it.first().id)
            }
        } else {
            GlobalScope.launch(Dispatchers.Main) {
                showErrorMessage(response)
            }
        }
    }

    private fun getInquiries(id: Long, direction: String) = runBlocking {
        try {
            ParayoApi.instance.getInquiries(
                id,
                productId,
                requestUserId,
                productOwnerId,
                direction
            )
        } catch (e: Exception) {
            ApiResponse.error<List<InquiryResponse>>(
                "알 수 없는 오류가 발생했습니다."
            )
        }
    }

    private fun showErrorMessage(
        response: ApiResponse<List<InquiryResponse>>
    ) {
        App.instance.toast(
            response.message ?: "알 수 없는 오류가 발생했습니다"
        )
    }

    companion object {
        private const val NEXT = "next"
        private const val PREV = "prev"
    }

}