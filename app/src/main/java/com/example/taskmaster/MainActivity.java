package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

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
        AppDataBase db = Room.databaseBuilder(getApplicationContext(),
                AppDataBase.class, "task_master").allowMainThreadQueries().build();

        TaskModelReposotery taskDao = db.taskDao();
        List<TaskModel> tasks = taskDao.getAll();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        MyAdapter adapter = new MyAdapter(tasks);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();


        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");

        TextView textView = findViewById(R.id.user);
        textView.setText(userName);
    }

    public void displayTitle(View view) {

        Button buttonTitle = (Button) view;
        String title = buttonTitle.getText().toString();
        Intent titleIntent = new Intent(MainActivity.this, DisplayTaskDetail.class);
        titleIntent.putExtra("title", title);
        startActivity(titleIntent);
    }

    public void displayAllTask(View view) {
        Intent intent = new Intent(this, DisplayAllTask.class);
        startActivity(intent);

    }

    public void displayAddTask(View view) {
        Intent intent = new Intent(this, DisplayAddTask.class);
        startActivity(intent);

    }

    public void displaySetting(View view) {
        Intent intent = new Intent(this, DisplaySettingsPage.class);
        startActivity(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();

        AppDataBase db = Room.databaseBuilder(getApplicationContext(),
                AppDataBase.class, "task_master").allowMainThreadQueries().build();

        TaskModelReposotery taskDao = db.taskDao();
        List<TaskModel> tasks = taskDao.getAll();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        MyAdapter adapter = new MyAdapter(tasks);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }
}