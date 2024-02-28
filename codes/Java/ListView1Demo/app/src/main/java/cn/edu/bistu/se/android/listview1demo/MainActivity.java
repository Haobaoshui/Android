package cn.edu.bistu.se.android.listview1demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] items=getResources().getStringArray(R.array.items);
        ArrayAdapter<String> arrayAdapter=new
                ArrayAdapter<String>(this,R.layout.listview_item,items);
        ListView listView=findViewById(R.id.listview);
        listView.setAdapter(arrayAdapter);
    }
}