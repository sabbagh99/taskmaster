package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class DisplayAddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_add_task);


        AppDataBase db = Room.databaseBuilder(getApplicationContext(),
                AppDataBase.class, "task_master").allowMainThreadQueries().build();

        TaskModelReposotery taskDao = db.taskDao();


        Toast toast = Toast.makeText(getApplicationContext(), "submitted!", Toast.LENGTH_LONG);
        Button bt = (Button) findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast.show();

                EditText title = findViewById(R.id.taskTitle);
                EditText description = findViewById(R.id.taskdesc);
                EditText status = findViewById(R.id.status);
                TaskModel task = new TaskModel(title.getText().toString(),description.getText().toString(),status.getText().toString());
                taskDao.insertAll(task);
                Log.d("taskssss", "onClick: "+task);
            }
        });




    }}