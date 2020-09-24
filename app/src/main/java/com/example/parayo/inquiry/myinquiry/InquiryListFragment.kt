package com.example.parayo.inquiry.myinquiry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import net.codephobia.ankomvvm.components.BaseFragment
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx

class InquiryListFragment: BaseFragment<InquiryListViewModel>() {

    override val viewModelType = InquiryListViewModel::class

    val inquiryPage get() = arguments?.getString(PAGE)?.let {
        InquiryPage.valueOf(it)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = InquiryListUI(getViewModel().apply { page = inquiryPage })
        .createView(AnkoContext.create(ctx, this))

    companion object {
        const val PAGE = "page"

        fun newInstance(inquiryPage: InquiryPage) =
            InquiryListFragment().apply {
                arguments = Bundle().also {
                    it.putString(PAGE, inquiryPage.name)
                }
            }
    }

}