package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Task;

import java.util.ArrayList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");

        TextView textView = findViewById(R.id.user);
        textView.setText(userName);

//        initialize Amplify
//        try {
//            Amplify.addPlugin(new AWSDataStorePlugin());
//            Amplify.configure(getApplicationContext());
//
//            Log.i("Tutorial", "Initialized Amplify");
//        } catch (AmplifyException e) {
//            Log.e("Tutorial", "Could not initialize Amplify", e);
//        }


//        Amplify.DataStore.query(Task.class,
//                todos -> {
//                    while (todos.hasNext()) {
//                        Task todo = todos.next();
//
//                        Log.i("Tutorial", "==== Todo ====");
//                        Log.i("Tutorial", "Title: " + todo.getTitle());
//
//                        if (todo.getStatus() != null) {
//                            Log.i("Tutorial", "status: " + todo.getStatus());
//                        }
//
//                        if (todo.getDescription() != null) {
//                            Log.i("Tutorial", "Description: " + todo.getDescription());
//                        }
//                    }
//                },
//                failure -> Log.e("Tutorial", "Could not query DataStore", failure)
//        );

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


}