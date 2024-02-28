package cn.edu.bistu.cs.se.roomsimpledemo

import android.app.Application
import timber.log.Timber

class RoomSimpleDemoApp : Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}