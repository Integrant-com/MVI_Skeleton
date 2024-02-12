package com.example.skeleton

import android.content.Context
import android.content.res.Configuration
import androidx.multidex.MultiDexApplication
import com.example.skeleton.extention.changeLanguage
import java.lang.ref.WeakReference

class SkeletonApplication : MultiDexApplication() {

    companion object {
        private var context: WeakReference<Context>? = null
        fun getContext(): Context? = context?.get()
    }

    private lateinit var initializer: Initializer

    override fun onCreate() {
        super.onCreate()
        context = WeakReference(applicationContext)
        initializer = Initializer(this)
        initializer.initKoin()
    }

    override fun attachBaseContext(base: Context) {
        val ctx = base.changeLanguage("en")
        super.attachBaseContext(ctx)
        //  MultiDex.install(this)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {

        super.onConfigurationChanged(newConfig)
        changeLanguage("en")
    }
}

