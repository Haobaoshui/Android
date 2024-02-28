package cn.edu.bistu.cs.se.filedemo

import android.app.Application
import timber.log.Timber

class FileDemoApp:Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}