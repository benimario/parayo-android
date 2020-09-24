package com.example.parayo.product

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.parayo.R
import com.example.parayo.product.list.ProductListPagerAdapter
import net.codephobia.ankomvvm.components.BaseActivity
import org.jetbrains.anko.setContentView

class ProductMainActivity :
    BaseActivity<ProductMainViewModel>() {

    override val viewModelType = ProductMainViewModel::class

    private val ui by lazy { ProductMainUI(getViewModel()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui.setContentView(this)
        ui.viewpager.adapter =
            ProductListPagerAdapter(supportFragmentManager)
        ui.tablayout.setupWithViewPager(ui.viewpager)
        setupDrawerListener()
    }

    private fun setupDrawerListener() {
        val toggle = ActionBarDrawerToggle(
            this,
            ui.drawerLayout,
            ui.toolBar,
            R.string.open_drawer_description,
            R.string.close_drawer_description
        )
        ui.drawerLayout.addDrawerListener(toggle)

        toggle.syncState()
    }

//    override fun onBackPressed() {
//        if (ui.drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            ui.drawerLayout.closeDrawer(GravityCompat.START)
//        } else {
//            super.onBackPressed()
//        }
//    }

}