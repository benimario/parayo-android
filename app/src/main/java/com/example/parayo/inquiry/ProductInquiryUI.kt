package com.example.parayo.inquiry

import android.view.Gravity
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parayo.inquiry.registration.InquiryRegistrationActivity
import net.codephobia.ankomvvm.databinding.bindPagedList
import net.codephobia.ankomvvm.databinding.bindVisibility
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.sdk27.coroutines.onClick

class ProductInquiryUI(
    private val viewModel: ProductInquiryViewModel
) : AnkoComponent<ProductInquiryActivity> {
    override fun createView(ui: AnkoContext<ProductInquiryActivity>) =
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
            }.lparams(matchParent, matchParent) {
                weight = 1f
            }
            textView("상품 문의가 없습니다.") {
                gravity = Gravity.CENTER
                bindVisibility(ui.owner, viewModel.inquiries) {
                    it.isEmpty()
                }
            }.lparams(wrapContent, matchParent) {
                weight = 1f
                gravity = Gravity.CENTER
            }

            button("문의하기") {
                textAlignment = Button.TEXT_ALIGNMENT_CENTER
                onClick {
                    viewModel.inquiry(
                        InquiryRegistrationActivity.TYPE_QUESTION
                    )
                }
            }.lparams(matchParent) {
                weight = 0f
            }
        }
}