package com.example.catapi

import android.app.Application
import com.example.catapi.di.moduleTech
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class CatInitApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CatInitApplication)
            modules(moduleTech)
        }
    }
}