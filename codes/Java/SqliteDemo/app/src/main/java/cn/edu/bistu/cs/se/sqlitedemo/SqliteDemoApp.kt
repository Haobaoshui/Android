package cn.edu.bistu.cs.se.sqlitedemo

import android.app.Application
import timber.log.Timber

class SqliteDemoApp : Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}