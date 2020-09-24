package com.example.parayo.product

import android.graphics.Typeface
import android.view.View
import com.example.parayo.R
import com.example.parayo.common.Prefs
import com.example.parayo.view.borderBottom
import org.jetbrains.anko.*

class ProductMainNavHeader : AnkoComponent<View> {

    override fun createView(ui: AnkoContext<View>) =
        ui.verticalLayout {
            padding = dip(20)
            background = borderBottom(width = dip(1))

            imageView(R.drawable.ic_logo)
                .lparams(dip(54), dip(54))

            textView(Prefs.userName) {
                topPadding = dip(8)
                textSize = 20f
                typeface = Typeface.DEFAULT_BOLD
            }
        }

}