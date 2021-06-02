package com.example.taskmaster;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class DisplayAddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_add_task);



        AppDataBase db = Room.databaseBuilder(getApplicationContext(),
                AppDataBase.class, "task_master").allowMainThreadQueries().build();

        TaskModelReposotery taskDao = db.taskDao();


//        try {
//            Amplify.addPlugin(new AWSDataStorePlugin());
//            Amplify.addPlugin(new AWSCognitoAuthPlugin());
//            Amplify.addPlugin(new AWSS3StoragePlugin());
//
//            Amplify.configure(getApplicationContext());
//
//            Log.i("Tutorial", "Initialized Amplify");
//        } catch (AmplifyException e) {
//            Log.e("Tutorial", "Could not initialize Amplify", e);
//        }


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


        Button bt2 = (Button) findViewById(R.id.button2);

bt2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        uploadFile();
    }
});


    }


    public void uploadFile() {
        File exampleFile = new File(getApplicationContext().getFilesDir(), "ExampleKey");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(exampleFile));
            writer.append("Example file contents");
            writer.close();
        } catch (Exception exception) {
            Log.e("MyAmplifyApp", "Upload failed", exception);
        }

        Amplify.Storage.uploadFile(
                "ExampleKey",
                exampleFile,
                result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
                storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
        );
    }




}

