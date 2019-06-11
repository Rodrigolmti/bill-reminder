package com.vortex.billreminder

import android.app.Application
import com.vortex.billreminder.di.dataModule
import com.vortex.billreminder.di.viewModelModule
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(dataModule, viewModelModule))
    }
}