package cn.edu.bistu.se.android.startactivityforresultapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    final int REQUEST_CODE_STUDENT_ZHANGSAN = 0;
    final int REQUEST_CODE_STUDENT_LISI = 1;
    final int REQUEST_CODE_STUDENT_TEACHER = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //启动StudentActivity
        Button buttonStudentZhangSan = findViewById(R.id.buttonStudentZhangSan);
        buttonStudentZhangSan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StudentActivity.class);
                intent.putExtra("name", "ZhangSan");
                startActivityForResult(intent, REQUEST_CODE_STUDENT_ZHANGSAN);
            }
        });

        //启动StudentActivity
        Button buttonStudentLiSi = findViewById(R.id.buttonStudentLiSi);
        buttonStudentLiSi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StudentActivity.class);
                intent.putExtra("name", "LiSi");
                startActivityForResult(intent, REQUEST_CODE_STUDENT_LISI);
            }
        });

        //启动TeacherActivity
        Button buttonTeacher = findViewById(R.id.buttonTeacher);
        buttonTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TeacherActivity.class);
                intent.putExtra("name", "teacher");
                startActivityForResult(intent, REQUEST_CODE_STUDENT_TEACHER);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        TextView textView = findViewById(R.id.textviewReceivedData);
        switch (requestCode) {
            case REQUEST_CODE_STUDENT_ZHANGSAN:
                if (resultCode == 0) textView.setText( data.getStringExtra("name") + "处理成功");
                else textView.setText(data.getStringExtra("name") + "处理失败");
                break;
            case REQUEST_CODE_STUDENT_LISI:
                if (resultCode == 0) textView.setText( data.getStringExtra("name") + "处理成功");
                else textView.setText(data.getStringExtra("name") + "处理失败");
                break;
            case REQUEST_CODE_STUDENT_TEACHER:
                textView.setText(data.getStringExtra("name") + "完成处理");

                break;
        }
    }
}