package com.example.skeleton

import android.content.Context
import com.example.skeleton.di.appComponents
import com.example.skeleton.di.networkComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class Initializer(private val context: Context) {
    fun initKoin() {
        startKoin {
            androidContext(context)
            modules(
                appComponents +
                        networkComponent
            )
        }
    }
}