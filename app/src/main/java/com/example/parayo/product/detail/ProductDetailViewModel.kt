package com.example.parayo.product.detail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.parayo.api.ParayoApi
import com.example.parayo.api.response.ApiResponse
import com.example.parayo.api.response.ProductResponse
import com.example.parayo.inquiry.ProductInquiryActivity
import com.example.parayo.product.ProductStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.codephobia.ankomvvm.databinding.addAll
import net.codephobia.ankomvvm.lifecycle.BaseViewModel
import org.jetbrains.anko.error
import java.text.NumberFormat

class ProductDetailViewModel(app: Application) : BaseViewModel(app) {

    var productId: Long? = null

    val productName = MutableLiveData("-")
    val description = MutableLiveData("")
    val price = MutableLiveData("-")
    val imageUrls: MutableLiveData<MutableList<String>> =
        MutableLiveData(mutableListOf())

    fun loadDetail(id: Long) = viewModelScope.launch(Dispatchers.Main) {
        try {
            productId = id
            val response = getProduct(id)
            if (response.success && response.data != null) {
                updateViewData(response.data)
            } else {
                toast(response.message ?: "알 수 없는 오류가 발생했습니다.")
            }
        } catch (e: Exception) {
            toast(e.message ?: "알 수 없는 오류가 발생했습니다.")
        }
    }

    private suspend fun getProduct(id: Long) = try {
        ParayoApi.instance.getProduct(id)
    } catch (e: Exception) {
        error("상품 정보를 가져오는 중 오류 발생.", e)
        ApiResponse.error<ProductResponse>(
            "상품 정보를 가져오는 중 오류가 발생했습니다."
        )
    }

    private fun updateViewData(product: ProductResponse) {
        val commaSeparatedPrice =
            NumberFormat.getInstance().format(product.price)
        val soldOutString =
            if(ProductStatus.SOLD_OUT == product.status) "(품절)" else ""

        productName.value = product.name
        description.value = product.description
        price.value =
            "₩${commaSeparatedPrice} $soldOutString"
        imageUrls.addAll(product.imagePaths)
    }

    fun openInquiryActivity() {
        startActivity<ProductInquiryActivity> {
            putExtra(ProductInquiryActivity.PRODUCT_ID, productId)
        }
    }

}