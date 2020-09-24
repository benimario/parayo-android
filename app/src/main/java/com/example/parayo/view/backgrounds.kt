package com.example.parayo.view

import android.graphics.Color
import android.graphics.drawable.*
import androidx.annotation.ColorInt
import androidx.annotation.ColorLong

private fun borderBG(
    borderColor: String = "#1F000000",
    bgColor: String = "#FFFFFF",
    borderLeftWidth: Int = 0,
    borderTopWidth: Int = 0,
    borderRightWidth: Int = 0,
    borderBottomWidth: Int = 0
): LayerDrawable {
    val layerDrawable = arrayOf<Drawable>(
        ColorDrawable(Color.parseColor(borderColor)),
        ColorDrawable(Color.parseColor(bgColor))
    ).let(::LayerDrawable)

    layerDrawable.setLayerInset(
        1,
        borderLeftWidth,
        borderTopWidth,
        borderRightWidth,
        borderBottomWidth
    )

    return layerDrawable
}

fun borderLeft(
    color: String = "#1F000000",
    width: Int
) = borderBG(
    borderColor = color,
    borderLeftWidth = width
)

fun borderTop(
    color: String = "#1F000000",
    width: Int
) = borderBG(
    borderColor = color,
    borderTopWidth = width
)

fun borderRight(
    color: String = "#1F000000",
    width: Int
) = borderBG(
    borderColor = color,
    borderRightWidth = width
)

fun borderBottom(
    color: String = "#1F000000",
    width: Int
) = borderBG(
    borderColor = color,
    borderBottomWidth = width
)

fun stroke(
    color: String = "#1F000000",
    width: Int
) = borderBG(
    borderColor = color,
    borderLeftWidth = width,
    borderTopWidth = width,
    borderRightWidth = width,
    borderBottomWidth = width
)

fun enabledStateButton(
    @ColorLong normalColor: Long,
    @ColorLong enabledColor: Long
) = StateListDrawable().apply {
    addState(arrayOf(android.R.attr.state_enabled).toIntArray(),
        GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadius = 8f
            setColor(enabledColor.toInt())
        })
    addState(IntArray(0),
        GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            cornerRadius = 8f
            setColor(normalColor.toInt())
        })
}

fun buttonPrimaryBG() = enabledStateButton(0xFFFFFFFF, 0xFF008577)
