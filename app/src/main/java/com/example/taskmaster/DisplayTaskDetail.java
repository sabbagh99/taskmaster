package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayTaskDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_task_detail);


        Intent intent = getIntent();
        String title = intent.getStringExtra("title");

        // Capture the layout's TextView and set the string as its text
        TextView titleView = findViewById(R.id.taskDetailTitle);
        titleView.setText(title);
    }
}