package cn.edu.bistu.cs.se.helloworld

import android.app.Application
import timber.log.Timber

class HelloWorldApp:Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}