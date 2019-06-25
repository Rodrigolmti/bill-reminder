package com.vortex.billreminder.presentation.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.vortex.billreminder.R
import com.vortex.billreminder.presentation.MainActivity
import com.vortex.billreminder.presentation.bill_list.ListBillViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private val viewModel by viewModel<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycle.addObserver(viewModel)

        viewModel.errorLiveData.observe(this, Observer {

        })

        viewModel.onValueSavedLiveData.observe(this, Observer {
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        })
    }
}
