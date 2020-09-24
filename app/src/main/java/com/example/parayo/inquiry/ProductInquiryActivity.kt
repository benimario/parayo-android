package com.example.parayo.inquiry

import android.os.Bundle
import net.codephobia.ankomvvm.components.BaseActivity
import org.jetbrains.anko.setContentView

class ProductInquiryActivity : BaseActivity<ProductInquiryViewModel>() {

    override val viewModelType = ProductInquiryViewModel::class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val productId = intent.getLongExtra(PRODUCT_ID, -1)
        val viewModel = getViewModel()
        viewModel.productId = productId

        ProductInquiryUI(viewModel).setContentView(this)
    }

    companion object {
        const val PRODUCT_ID = "productId"
    }

}