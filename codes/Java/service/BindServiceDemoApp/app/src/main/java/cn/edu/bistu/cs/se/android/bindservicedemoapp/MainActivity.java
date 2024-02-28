package cn.edu.bistu.cs.se.android.bindservicedemoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.security.Provider;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "TAG";

    ServiceConnection mServiceConnection;
    CalcService mCalcService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.v(TAG, "onServiceConnected");
                mCalcService = ((CalcService.LocalBinder) iBinder).getService();
            }

            /**
             * 注意：在连接正常关闭的情况下是该函数不会被调用的
             * 只有在Service被破坏了或者被杀死的时候调用. 例如如果系统资源不足, 要关闭绑定的 Service时，onServiceDisconnected() 就会被调用
             * @param componentName
             */
            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.v(TAG, "onServiceDisconnected");
            }
        };
    }

    /**
     * 绑定服务
     *
     * @param view
     */
    public void onButtonStartServiceClick(View view) {
        Intent intent = new Intent(MainActivity.this, CalcService.class);
        bindService(intent, mServiceConnection, Service.BIND_AUTO_CREATE);
    }

    /**
     * 解绑服务
     *
     * @param view
     */
    public void onButtonStopServiceClick(View view) {
        if (mCalcService != null) unbindService(mServiceConnection);
    }

    /**
     * 使用服务的加法功能
     *
     * @param view
     */
    public void onButtonAddClick(View view) {
        if (mCalcService != null)
            Toast.makeText(MainActivity.this, "Using service add:" + mCalcService.add(10, 5), Toast.LENGTH_LONG).show();
    }

    /**
     * 使用服务的减法功能
     *
     * @param view
     */
    public void onButtonSubClick(View view) {
        if (mCalcService != null)
            Toast.makeText(MainActivity.this, "Using service sub:" + mCalcService.sub(10, 5), Toast.LENGTH_LONG).show();
    }

    /**
     * 使用服务的乘法功能
     *
     * @param view
     */
    public void onButtonMulClick(View view) {
        if (mCalcService != null)
            Toast.makeText(MainActivity.this, "Using service mul:" + mCalcService.mul(10, 5), Toast.LENGTH_LONG).show();
    }

    /**
     * 使用服务的除法功能
     *
     * @param view
     */
    public void onButtonDivClick(View view) {
        if (mCalcService != null)
            Toast.makeText(MainActivity.this, "Using service div:" + mCalcService.div(10, 5), Toast.LENGTH_LONG).show();
    }


}