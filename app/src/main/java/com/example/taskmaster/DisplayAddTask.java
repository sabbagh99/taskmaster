package com.example.taskmaster;

import androidx.annotation.RequiresApi;
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
import android.os.Build;
import android.os.Bundle;
import android.os.FileUtils;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DisplayAddTask extends AppCompatActivity {
    private ImageView imageView;

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
                uploadFileFromMobile();
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1234) {
            File file = new File(getApplicationContext().getFilesDir(), "upload");

            try {
                InputStream inputStream = getContentResolver().openInputStream(data.getData());
//                Bitmap inputStream = (Bitmap) data.getExtras().get("data");

                FileUtils.copy(inputStream,new FileOutputStream(file));
                uploadFile(inputStream,"test");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void uploadFileFromMobile() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("*/*");
        startActivityForResult(intent, 1234);


//        Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(pickPhoto);

    }

    public void uploadFile(InputStream file, String filename) {
//        File exampleFile = new File(getApplicationContext().getFilesDir(), filename);
//
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter(exampleFile));
//            writer.append(file);
//            writer.close();
//        } catch (Exception exception) {
//            Log.e("MyAmplifyApp", "Upload failed", exception);
//        }

        Amplify.Storage.uploadInputStream(
                filename,
                file,
                result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
                storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
        );
    }



}

