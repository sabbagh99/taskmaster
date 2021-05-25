package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class DisplayAllTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all_task);

//
//        AppDataBase db = Room.databaseBuilder(getApplicationContext(),
//                AppDataBase.class, "task_master").allowMainThreadQueries().build();
//
//        TaskModelReposotery taskDao = db.taskDao();
//        List<TaskModel> tasks = taskDao.getAll();


//        taskDao.nukeTable();


//        ========================== AWS database ==================

//        initialize Amplify
        try {
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.configure(getApplicationContext());

            Log.i("Tutorial", "Initialized Amplify");
        } catch (AmplifyException e) {
            Log.e("Tutorial", "Could not initialize Amplify", e);
        }
       List<TaskModel> allTask = new ArrayList<TaskModel>();

        Amplify.DataStore.query(Task.class,
                todos -> {
                    while (todos.hasNext()) {
                        Task todo = todos.next();

                        Log.i("Tutorial", "==== Todo ====");
                        Log.i("Tutorial", "Title: " + todo.getTitle());
                        String  title = todo.getTitle();
                        String status = "";
                        String description = "";

                        if (todo.getStatus() != null) {
                            Log.i("Tutorial", "status: " + todo.getStatus());
                             status = todo.getStatus();
                        }

                        if (todo.getDescription() != null) {
                            Log.i("Tutorial", "Description: " + todo.getDescription());
                             description = todo.getDescription();
                        }
                        allTask.add( new TaskModel(title, description, status));
                    }
                },
                failure -> Log.e("Tutorial", "Could not query DataStore", failure)
        );


//        ===============================================================

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        MyAdapter adapter = new MyAdapter(allTask);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onResume() {
        super.onResume();

//        AppDataBase db = Room.databaseBuilder(getApplicationContext(),
//                AppDataBase.class, "task_master").allowMainThreadQueries().build();
//
//        TaskModelReposotery taskDao = db.taskDao();
//        List<TaskModel> tasks = taskDao.getAll();
//
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        MyAdapter adapter = new MyAdapter(tasks);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(adapter);
//
//        adapter.notifyDataSetChanged();



//        ========================== AWS database ==================

//        initialize Amplify
        try {
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.configure(getApplicationContext());

            Log.i("Tutorial", "Initialized Amplify");
        } catch (AmplifyException e) {
            Log.e("Tutorial", "Could not initialize Amplify", e);
        }
        List<TaskModel> allTask = new ArrayList<TaskModel>();

        Amplify.DataStore.query(Task.class,
                todos -> {
                    while (todos.hasNext()) {
                        Task todo = todos.next();

                        Log.i("Tutorial", "==== Todo ====");
                        Log.i("Tutorial", "Title: " + todo.getTitle());
                        String  title = todo.getTitle();
                        String status = "";
                        String description = "";

                        if (todo.getStatus() != null) {
                            Log.i("Tutorial", "status: " + todo.getStatus());
                            status = todo.getStatus();
                        }

                        if (todo.getDescription() != null) {
                            Log.i("Tutorial", "Description: " + todo.getDescription());
                            description = todo.getDescription();
                        }
                        allTask.add( new TaskModel(title, description, status));
                    }
                },
                failure -> Log.e("Tutorial", "Could not query DataStore", failure)
        );


//        ===============================================================

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        MyAdapter adapter = new MyAdapter(allTask);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }
}