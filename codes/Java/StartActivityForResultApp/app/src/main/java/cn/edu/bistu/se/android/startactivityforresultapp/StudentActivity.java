package cn.edu.bistu.se.android.startactivityforresultapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StudentActivity extends AppCompatActivity {

    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        //接收到的数据
        final Intent intent = getIntent();
        name = intent.getStringExtra("name");
        TextView textView = findViewById(R.id.textviewReceivedData);
        textView.setText(name);

        //返回成功
        Button buttonSucceed = findViewById(R.id.buttonReturnSucceed);
        buttonSucceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent.putExtra("result", "name:" + name);
                setResult(0, intent);
                finish();
            }
        });

        //返回失败
        Button buttonFailed = findViewById(R.id.buttonReturnFailed);
        buttonFailed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent.putExtra("result", "name:" + name);
                setResult(1, intent);
                finish();
            }
        });

    }
}