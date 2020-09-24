package com.example.parayo.extensions

import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import org.jetbrains.anko.matchParent

inline fun <reified F : ViewGroup.LayoutParams> View.lparams(
    init: F.() -> Unit
) {
    val layoutParams = F::class.java
        .getDeclaredConstructor(Int::class.java, Int::class.java)
        .newInstance(matchParent, matchParent)
    layoutParams.init()

    if (layoutParams is ConstraintLayout.LayoutParams)
        layoutParams.validate()

    this@lparams.layoutParams = layoutParams
}