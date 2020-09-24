package com.example.parayo.signup

import android.os.Bundle
import net.codephobia.ankomvvm.components.BaseActivity
import org.jetbrains.anko.setContentView

class SignupActivity : BaseActivity<SignupViewModel>() {

    override val viewModelType = SignupViewModel::class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SignupActivityUI(getViewModel())
            .setContentView(this)
    }

}