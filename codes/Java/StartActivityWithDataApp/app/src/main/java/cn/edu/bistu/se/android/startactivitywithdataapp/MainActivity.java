package cn.edu.bistu.se.android.startactivitywithdataapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.edu.bistu.se.android.startactivitywithdataapp.model.Student;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button=findViewById(R.id.buttonStartAnotherActivity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AnotherActivity.class);
                intent.putExtra("name","Zhangsan");//字符串
                intent.putExtra("age",20);//整数

                Student student=new Student();
                student.setName("Lisi");
                student.setAge(20);
                intent.putExtra("student",student);//对象

                startActivity(intent);
            }
        });


    }
}