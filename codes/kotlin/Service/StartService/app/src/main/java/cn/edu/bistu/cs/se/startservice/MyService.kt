package cn.edu.bistu.cs.se.startservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyService : Service() {
    private val TAG="serviceTAG"

    override fun onCreate() {
        super.onCreate()
        Log.v(TAG,"Service onCreate()");
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(TAG,"Service onDestroy()");
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val num= intent?.getIntExtra("num",0)
        Log.v(TAG,"Service onStartCommand(),接收的值为:"+num);
        return super.onStartCommand(intent, flags, startId)
    }


}