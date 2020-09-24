package com.example.parayo.inquiry.registration

import android.graphics.Typeface
import android.view.Gravity
import net.codephobia.ankomvvm.databinding.bindString
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class InquiryRegistrationUI(
    private val viewModel: InquiryRegistrationViewModel
) : AnkoComponent<InquiryRegistrationActivity> {

    override fun createView(ui: AnkoContext<InquiryRegistrationActivity>) =
        ui.verticalLayout {
            padding = dip(20)

            editText {
                padding = dip(10)
                hint = "내용"
                gravity = Gravity.TOP
                maxLines = 6
                minLines = 6
                backgroundColor = 0xF7F7F7F7.toInt()
                textSize = 16f
                bindString(ui.owner, viewModel.content)
            }.lparams(matchParent, wrapContent) {
                bottomMargin = dip(20)
            }
            button("등록") {
                textSize = 16f
                typeface = Typeface.DEFAULT_BOLD
                onClick {
                    viewModel.register()
                }
            }
        }

}