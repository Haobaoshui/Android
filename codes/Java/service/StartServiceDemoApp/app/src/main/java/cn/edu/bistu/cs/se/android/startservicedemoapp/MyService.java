package cn.edu.bistu.cs.se.android.startservicedemoapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG="serviceTAG";

    public MyService() {
    }

    @Override
    public void onCreate() {
        Log.v(TAG,"Service onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(TAG,"Service onStartCommand");
        int num=intent.getIntExtra("num",0);
        Log.v(TAG,"Parameter value is:"+num);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.v(TAG,"Service onDestroy");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
