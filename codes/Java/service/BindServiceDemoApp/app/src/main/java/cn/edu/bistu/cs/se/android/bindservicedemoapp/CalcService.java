package cn.edu.bistu.cs.se.android.bindservicedemoapp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class CalcService extends Service {
    private static final String TAG = "TAG";

    private LocalBinder mLocalBinder = new LocalBinder();

    /**
     * Binder对象实现了IBinder接口
     */
    public class LocalBinder extends Binder {
        CalcService getService() {
            return CalcService.this;
        }
    }

    public int add(int x, int y) {
        Log.v(TAG, "Service add");
        return x + y;
    }

    public int sub(int x, int y) {
        Log.v(TAG, "Service sub");
        return x - y;
    }

    public int mul(int x, int y) {
        Log.v(TAG, "Service mul");
        return x * y;
    }

    public int div(int x, int y) {
        Log.v(TAG, "Service div");
        if (y != 0) return x / y;
        throw new ArithmeticException("Divide by zero");
    }


    public CalcService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.v(TAG, "Service onBind");
        return mLocalBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.v(TAG, "Service onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onCreate() {
        Log.v(TAG, "Service onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(TAG, "Service onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        Log.v(TAG, "Service onDestroy");
        super.onDestroy();
    }
}
