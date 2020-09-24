package com.example.parayo.product.registration

import com.example.parayo.api.ParayoApi
import com.example.parayo.api.request.ProductRegistrationRequest
import com.example.parayo.api.response.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import retrofit2.Response

class ProductRegistrar : AnkoLogger {

    suspend fun register(request: ProductRegistrationRequest) = when {
        request.isNotValidName ->
            ApiResponse.error("상품명을 조건에 맞게 입력해주세요.")
        request.isNotValidDescription ->
            ApiResponse.error("상품 설명을 조건에 맞게 입력해주세요.")
        request.isNotValidPrice ->
            ApiResponse.error("가격을 조건에 맞게 입력해주세요.")
        request.isNotValidCategoryId ->
            ApiResponse.error("카테고리 아이디를 선택해주세요.")
        request.isNotValidImageIds ->
            ApiResponse.error("이미지를 한개 이상 등록해주세요.")
        else -> withContext(Dispatchers.IO) {
            try {
                ParayoApi.instance.registerProduct(request)
            } catch (e: Exception) {
                error("상품 등록 오류.", e)
                ApiResponse.error<Response<Void>>(
                    "알 수 없는 오류가 발생했습니다."
                )
            }
        }
    }

}