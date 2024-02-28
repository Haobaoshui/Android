package com.example.okhttptest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.okhttptest.model.User;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    //

    /**
     * 服务器url
     * 注意：如果是在自己电脑上调试的话，把192.168.0.106替换成自己电脑的ip地址。
     * 进一步说明：在自己的电脑上调试，即把自己的电脑当作服务器。Android（无论真机还是模拟器）访问服务器为跨机访问，因此不能像Web端那样访问localhost(因为对于Android程序而言，localhost为手机自己，不是spring boot所在的服务器)。
     */
    private final String SERVER_URL = "http://192.168.0.106:8000/user/v1/add";

    private final String TAG = "test";

    static void get() {
        String name = "abc";
        String password = "123";
        String path = "http://192.168.43.227:8080/demo_android/login.action?username=" + name + "&password=" + password;
    }

    /**
     * 添加用户
     */
    private void addUser() {
        Log.v(TAG, "post");
        User user = new User();
        user.setUserName("abc");
        user.setUserPassword("123");
        Gson gson = new Gson();
        post(SERVER_URL, gson.toJson(user));


    }

    /**
     * 向服务器以异步方式发送post请求
     *
     * @param url
     * @param json
     */
    private void post(String url, String json) {
        Log.v(TAG, json);
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody body = RequestBody.create(json, MainActivity.JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = okHttpClient.newCall(request);


        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Log.v(TAG, "onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Log.v(TAG, "onResponse");
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    //处理UI需要切换到UI线程处理
                    Log.v(TAG, result);
                }
            }
        });
    }

    void get(String url, String json) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    //处理UI需要切换到UI线程处理
                    Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG);
                }
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onClick(View view) {
        Log.v(TAG, "view");
        switch (view.getId()) {
            case R.id.AddUser:
                addUser();
                break;

            case R.id.DeleteUser:
                deleteUser();
                break;
            case R.id.UpdateUser:
                updateUser();
                break;
            case R.id.GetUser:
                getUser();
                break;
            case R.id.GetUserList:
                getUserList();
                break;
        }
    }

    /**
     * 删除用户
     */
    private void deleteUser() {

    }

    /**
     * 更新用户
     */
    private void updateUser() {

    }

    /**
     * 获得用户
     */
    private void getUser() {

    }

    /**
     * 得到用户列表
     */
    private void getUserList() {

    }
}
