package com.example.parayo.product.search

import android.os.Bundle
import android.view.MenuItem
import net.codephobia.ankomvvm.components.BaseActivity
import org.jetbrains.anko.setContentView

class ProductSearchActivity : BaseActivity<ProductSearchViewModel>() {

    override val viewModelType = ProductSearchViewModel::class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val keyword = intent.getStringExtra(KEYWORD)
        val viewModel = getViewModel().apply {
            this.keyword = keyword
        }

        ProductSearchUI(viewModel).setContentView(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = keyword
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return true
    }

    companion object {
        const val KEYWORD = "keyword"
    }

}