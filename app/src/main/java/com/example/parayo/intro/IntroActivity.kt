package com.example.parayo.intro

import android.app.Activity
import android.os.Bundle
import com.example.parayo.common.Prefs
import com.example.parayo.product.ProductMainActivity
import com.example.parayo.signin.SigninActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity

class IntroActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        IntroActivityUI().setContentView(this)

        GlobalScope.launch(Dispatchers.Main) {
            delay(1500)

            if(Prefs.token.isNullOrEmpty()) {
                startActivity<SigninActivity>()
            } else {
                startActivity<ProductMainActivity>()
            }

            finish()
        }
    }

}





