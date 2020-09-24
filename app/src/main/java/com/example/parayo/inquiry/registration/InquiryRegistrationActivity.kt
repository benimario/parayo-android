package com.example.parayo.inquiry.registration

import android.os.Bundle
import net.codephobia.ankomvvm.components.BaseActivity
import org.jetbrains.anko.setContentView

class InquiryRegistrationActivity :
    BaseActivity<InquiryRegistrationViewModel>() {

    override val viewModelType = InquiryRegistrationViewModel::class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val productId = intent.getLongExtra(PRODUCT_ID, -1)
        val inquiryId = intent.getLongExtra(INQUIRY_ID, -1)
        val inquiryType = intent.getStringExtra(INQUIRY_TYPE)

        val viewModel = getViewModel().apply {
            this.productId = productId
            this.inquiryId = inquiryId
            this.inquiryType = inquiryType
        }

        InquiryRegistrationUI(viewModel).setContentView(this)
    }

    companion object {
        const val TYPE_QUESTION = "QUESTION"
        const val TYPE_ANSWER = "ANSWER"

        const val PRODUCT_ID = "productId"
        const val INQUIRY_ID = "inquiryId"
        const val INQUIRY_TYPE = "inquiryType"
    }

}