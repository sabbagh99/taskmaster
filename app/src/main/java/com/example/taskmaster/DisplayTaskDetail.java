package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DisplayTaskDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_task_detail);


        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String disruption = intent.getStringExtra("disruption");
        String status = intent.getStringExtra("status");
        Log.d("stateFrag", "status: "+status);


        // Capture the layout's TextView and set the string as its text
        TextView titleView = findViewById(R.id.taskDetailTitle);
        titleView.setText(title);
        TextView disruptionView = findViewById(R.id.textView3);
        disruptionView.setText(disruption);
        TextView statusView = findViewById(R.id.textView7);
        statusView.setText(status);
    }
}