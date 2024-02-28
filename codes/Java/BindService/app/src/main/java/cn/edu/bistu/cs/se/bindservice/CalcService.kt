package cn.edu.bistu.cs.se.bindservice

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class CalcService : Service() {

    private val binder = LocalBinder()

    inner class LocalBinder : Binder() {
        // Return this instance of LocalService so clients can call public methods
        fun getService(): CalcService = this@CalcService
    }

    public fun add(x:Int,y:Int):Int{
        Log.v("BindService","add")
        return x+y
    }

    public fun sub(x:Int,y:Int):Int{
        Log.v("BindService","add")
        return x-y
    }

    override fun onCreate() {
        super.onCreate()
        Log.v("BindService","onCreate")
    }
    override fun onBind(intent: Intent): IBinder {
        Log.v("BindService","onBind")
        return binder
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v("BindService","onDestroy")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.v("BindService","onDestroy")
        return super.onStartCommand(intent, flags, startId)
    }
}