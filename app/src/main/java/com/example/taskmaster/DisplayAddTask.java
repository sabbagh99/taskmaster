package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Task;

import java.util.List;

public class DisplayAddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_add_task);


        AppDataBase db = Room.databaseBuilder(getApplicationContext(),
                AppDataBase.class, "task_master").allowMainThreadQueries().build();

        TaskModelReposotery taskDao = db.taskDao();

        try {
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.configure(getApplicationContext());

            Log.i("Tutorial", "Initialized Amplify");
        } catch (AmplifyException e) {
            Log.e("Tutorial", "Could not initialize Amplify", e);
        }


        Toast toast = Toast.makeText(getApplicationContext(), "submitted!", Toast.LENGTH_LONG);
        Button bt = (Button) findViewById(R.id.button);
//        bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                toast.show();
//
//                EditText title = findViewById(R.id.taskTitle);
//                EditText description = findViewById(R.id.taskdesc);
//                EditText status = findViewById(R.id.status);
//                TaskModel task = new TaskModel(title.getText().toString(),description.getText().toString(),status.getText().toString());
//                taskDao.insertAll(task);
//            }
//        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast.show();

                EditText title = findViewById(R.id.taskTitle);
                EditText description = findViewById(R.id.taskdesc);
                EditText status = findViewById(R.id.status);

                Task item = Task.builder()
                .title(title.getText().toString())
                .description(description.getText().toString())
                .status(status.getText().toString())
                .build();

        Amplify.DataStore.save(item,
                success -> Log.i("Tutorial", "Saved item: " + success.item().getTitle()),
                error -> Log.e("Tutorial", "Could not save item to DataStore", error)
        );

            }
        });






    }}