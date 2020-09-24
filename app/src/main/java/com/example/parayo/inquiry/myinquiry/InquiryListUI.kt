package com.example.parayo.inquiry.myinquiry

import android.view.Gravity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parayo.inquiry.InquiryPagedAdapter
import net.codephobia.ankomvvm.databinding.bindPagedList
import net.codephobia.ankomvvm.databinding.bindVisibility
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class InquiryListUI(
    private val viewModel: InquiryListViewModel
) : AnkoComponent<InquiryListFragment> {
    override fun createView(ui: AnkoContext<InquiryListFragment>) =
        ui.verticalLayout {
            recyclerView {
                layoutManager = LinearLayoutManager(ui.ctx)
                lparams(matchParent, matchParent)
                bindVisibility(ui.owner, viewModel.inquiries) {
                    it.isNotEmpty()
                }
                bindPagedList(
                    ui.owner,
                    InquiryPagedAdapter(viewModel),
                    viewModel.inquiries
                )
            }.lparams(matchParent, matchParent)

            textView("상품 문의가 없습니다.") {
                gravity = Gravity.CENTER
                bindVisibility(ui.owner, viewModel.inquiries) {
                    it.isEmpty()
                }
            }.lparams(wrapContent, matchParent) {
                gravity = Gravity.CENTER
            }
        }
}