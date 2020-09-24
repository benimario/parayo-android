package com.example.parayo.product.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.codephobia.ankomvvm.components.BaseFragment
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx

class ProductListFragment : BaseFragment<ProductListViewModel>() {

    override val viewModelType = ProductListViewModel::class

    val categoryId get() = arguments?.getInt("categoryId")
        ?: throw IllegalStateException("categoryId 없음")
    val title get() = arguments?.getString("title")
        ?: throw IllegalStateException("title 없음")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel = getViewModel()
        viewModel.categoryId = categoryId

        return ProductListUI(viewModel)
            .createView(AnkoContext.create(ctx, this))
    }

    companion object {
        fun newInstance(categoryId: Int, title: String) =
            ProductListFragment().apply {
                arguments = Bundle().also {
                    it.putInt("categoryId", categoryId)
                    it.putString("title", title)
                }
            }
    }

}