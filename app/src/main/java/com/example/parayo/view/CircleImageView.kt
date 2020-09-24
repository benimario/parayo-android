package com.example.parayo.view

import android.view.ViewManager
import android.widget.ImageView.ScaleType.CENTER_CROP
import de.hdodenhof.circleimageview.CircleImageView
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.imageResource

fun ViewManager.circleImageView(
    resource: Int
) = ankoView({ ctx ->
    CircleImageView(ctx).apply {
        imageResource = resource
        scaleType = CENTER_CROP
    }
}, 0) {
}
