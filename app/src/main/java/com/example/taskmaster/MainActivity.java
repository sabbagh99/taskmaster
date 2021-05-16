package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public   void  displayAllTask(View view){
        Intent intent = new Intent(this, DisplayAllTask.class);
        startActivity(intent);

    }

    public   void  displayAddTask(View view){
        Intent intent = new Intent(this, DisplayAddTask.class);
        startActivity(intent);

    }
}