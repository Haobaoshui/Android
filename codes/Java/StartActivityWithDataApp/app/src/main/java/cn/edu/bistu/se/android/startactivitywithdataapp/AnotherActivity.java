package cn.edu.bistu.se.android.startactivitywithdataapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import cn.edu.bistu.se.android.startactivitywithdataapp.model.Student;

public class AnotherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);

        //接收数据并显示
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        int age=intent.getIntExtra("age",20);
        Student student=(Student) intent.getSerializableExtra("student");

        TextView textView=findViewById(R.id.textviewReceivedData);
        textView.setText("name:"+name+",age:"+age+",student:"+student.getName()+","+student.getAge());

        Button button=findViewById(R.id.buttonReturn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}