package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//
//        Button buttonTitle = (Button) findViewById(R.id.task1);
//        String title = buttonTitle.getText().toString();
//
//        buttonTitle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent titleIntent = new Intent(MainActivity.this,DisplayTaskDetail.class);
//                titleIntent.putExtra("title",title);
//                startActivity(titleIntent);
//            }
//        });
        List<TaskModel> listData = new ArrayList<>();
        TaskModel task1 = new TaskModel("1","1","1");
        TaskModel task2 = new TaskModel("2","2","2");
        TaskModel task3 = new TaskModel("3","3","3");


        listData.add(task1);
        listData.add(task2);
        listData.add(task3);



        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        MyAdapter adapter = new MyAdapter(listData);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");

        TextView textView = findViewById(R.id.user);
        textView.setText(userName);
    }

    public  void displayTitle(View view){

        Button buttonTitle = (Button) view;
        String title = buttonTitle.getText().toString();
        Intent titleIntent = new Intent(MainActivity.this,DisplayTaskDetail.class);
        titleIntent.putExtra("title",title);
        startActivity(titleIntent);
    }
    public   void  displayAllTask(View view){
        Intent intent = new Intent(this, DisplayAllTask.class);
        startActivity(intent);

    }

    public   void  displayAddTask(View view){
        Intent intent = new Intent(this, DisplayAddTask.class);
        startActivity(intent);

    }
    public   void  displaySetting(View view){
        Intent intent = new Intent(this, DisplaySettingsPage.class);
        startActivity(intent);

    }
}