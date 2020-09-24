package com.example.parayo.product.detail

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.parayo.api.ApiGenerator
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.imageView

class ImageSliderAdapter : PagerAdapter() {

    var imageUrls: List<String> = listOf()

    override fun isViewFromObject(view: View, obj: Any) =
        view == obj

    override fun getCount() = imageUrls.size

    override fun instantiateItem(
        container: ViewGroup,
        position: Int
    ): Any {
        val view = AnkoContext.create(container.context, container)
            .imageView().apply {
                Glide.with(this)
                    .load("${ApiGenerator.HOST}${imageUrls[position]}")
                    .into(this)
            }
        container.addView(view)
        return view
    }

    fun updateItems(items: MutableList<String>) {
        imageUrls = items
        notifyDataSetChanged()
    }

    override fun destroyItem(
        container: ViewGroup,
        position: Int,
        obj: Any
    ) {
        container.invalidate()
    }

}