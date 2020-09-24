package com.example.parayo.inquiry.myinquiry

import android.app.Application
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.parayo.api.response.InquiryResponse
import com.example.parayo.common.Prefs
import com.example.parayo.inquiry.InquiryDataSource
import com.example.parayo.inquiry.InquiryPagedAdapter
import com.example.parayo.inquiry.registration.InquiryRegistrationActivity
import com.example.parayo.product.detail.ProductDetailActivity
import net.codephobia.ankomvvm.lifecycle.BaseViewModel

class InquiryListViewModel(
    app: Application
) : BaseViewModel(app),
    InquiryPagedAdapter.InquiryItemClickListener {

    var page: InquiryPage? = null
    var requestUserId: Long? = null
    var productOwnerId: Long? = null

    val inquiries by lazy {
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(false)
            .build()

        val factory =
            object : DataSource.Factory<Long, InquiryResponse>() {
                override fun create()
                    : DataSource<Long, InquiryResponse> {
                    when (page) {
                        InquiryPage.MY_INQUIRY ->
                            requestUserId = Prefs.userId
                        InquiryPage.PRODUCT_INQUIRY ->
                            productOwnerId = Prefs.userId
                    }
                    return InquiryDataSource(
                        requestUserId = requestUserId,
                        productOwnerId = productOwnerId
                    )
                }
            }

        LivePagedListBuilder(factory, config).build()
    }

    override fun onClickInquiry(inquiryResponse: InquiryResponse?) {
        inquiryResponse?.run {
            startActivity<ProductDetailActivity> {
                putExtra(
                    ProductDetailActivity.PRODUCT_ID,
                    productId
                )
            }
        }
    }

    override fun onClickAnswer(inquiryResponse: InquiryResponse?) {
        inquiryResponse?.run {
            startActivity<InquiryRegistrationActivity> {
                putExtra(InquiryRegistrationActivity.PRODUCT_ID, productId)
                putExtra(InquiryRegistrationActivity.INQUIRY_ID, id)
                putExtra(
                    InquiryRegistrationActivity.INQUIRY_TYPE,
                    InquiryRegistrationActivity.TYPE_ANSWER
                )
            }
        }
    }

}