package cn.edu.bistu.cs.se.sdfiledemo

import android.app.Application
import timber.log.Timber

class SDFileDemoApp:Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}