package cn.edu.bistu.se.android.startactivityforresultapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TeacherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        //接收到的数据
        final Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        TextView textView = findViewById(R.id.textviewReceivedData);
        textView.setText(name);

        //返回成功
        Button buttonSucceed = findViewById(R.id.buttonReturn);
        buttonSucceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("result", "name:" + name);
                setResult(0, intent);
                finish();
            }
        });
    }
}