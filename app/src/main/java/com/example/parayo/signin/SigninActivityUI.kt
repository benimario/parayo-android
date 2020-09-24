package com.example.parayo.signin

import android.graphics.Color
import android.graphics.Typeface
import android.text.InputType
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import com.example.parayo.R
import com.example.parayo.signup.SignupActivity
import net.codephobia.ankomvvm.databinding.bindString
import org.jetbrains.anko.*
import org.jetbrains.anko.design.textInputEditText
import org.jetbrains.anko.design.textInputLayout
import org.jetbrains.anko.sdk27.coroutines.onClick

class SigninActivityUI(
    private val viewModel: SigninViewModel
) : AnkoComponent<SigninActivity> {

    override fun createView(ui: AnkoContext<SigninActivity>) =
        ui.linearLayout {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_VERTICAL
            padding = dip(20)


            textView("Parayo") {
                textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                textSize = 24f
                typeface = Typeface.DEFAULT_BOLD
                textColorResource = R.color.colorPrimary
            }.lparams(width = matchParent) {
                bottomMargin = dip(50)
            }


            textInputLayout {
                textInputEditText {
                    hint = "Email"
                    setSingleLine()
                    bindString(ui.owner, viewModel.email)
                }
            }.lparams(width = matchParent) {
                bottomMargin = dip(20)
            }


            textInputLayout {
                textInputEditText {
                    hint = "Password"
                    setSingleLine()
                    inputType = InputType.TYPE_CLASS_TEXT or
                            InputType.TYPE_TEXT_VARIATION_PASSWORD
                    bindString(ui.owner, viewModel.password)
                }
            }.lparams(width = matchParent) {
                bottomMargin = dip(20)
            }


            button("로그인") {
                onClick { viewModel.signin() }
            }.lparams(width = matchParent)


            button("회원가입") {
                backgroundColor = Color.TRANSPARENT
                textColorResource = R.color.colorPrimary
                onClick { ui.startActivity<SignupActivity>() }
            }
        }

}