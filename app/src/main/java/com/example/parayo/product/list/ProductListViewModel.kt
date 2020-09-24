package com.example.parayo.product.list

import android.app.Application
import android.content.Intent
import androidx.paging.DataSource
import com.example.parayo.api.response.ProductListItemResponse
import com.example.parayo.product.detail.ProductDetailActivity
import net.codephobia.ankomvvm.lifecycle.BaseViewModel
import org.jetbrains.anko.error

class ProductListViewModel(
    app: Application
) : BaseViewModel(app),
    ProductListPagedAdapter.ProductLiveDataBuilder,
    ProductListPagedAdapter.OnItemClickListener {

    var categoryId: Int = -1
    val products = buildPagedList()

    override fun createDataSource(): DataSource<Long, ProductListItemResponse> {
        if (categoryId == -1)
            error(
                "categoryId가 설정되지 않았습니다.",
                IllegalStateException("categoryId is -1")
            )
        return ProductListItemDataSource(categoryId)
    }

    override fun onClickProduct(productId: Long?) {
        startActivity<ProductDetailActivity> {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            putExtra(ProductDetailActivity.PRODUCT_ID, productId)
        }
    }

}
