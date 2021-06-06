package com.example.taskmaster;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.auth.options.AuthSignOutOptions;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {

                        String token = task.getResult();

                        Log.d("token : ", token);
                        Toast.makeText(MainActivity.this, ": hello there ;) ", Toast.LENGTH_SHORT).show();
                    }
                });
        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName1");

        TextView textView = findViewById(R.id.user);
        textView.setText(userName);

//        initialize Amplify
        try {
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSS3StoragePlugin());

            Amplify.configure(getApplicationContext());

            Log.i("Tutorial", "Initialized Amplify");
        } catch (AmplifyException e) {
            Log.e("Tutorial", "Could not initialize Amplify", e);
        }


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

//====================================
//
//        try {
//            Amplify.addPlugin(new AWSDataStorePlugin());
//            Amplify.addPlugin(new AWSCognitoAuthPlugin());
//            Amplify.configure(getApplicationContext());
//
//            Log.i("Tutorial", "Initialized Amplify");
//        } catch (AmplifyException e) {
//            Log.e("Tutorial", "Could not initialize Amplify", e);
//        }


//        AuthSignUpOptions options = AuthSignUpOptions.builder()
//                .userAttribute(AuthUserAttributeKey.email(), "a.sameer1999@gmail.com")
//                .build();
//        Amplify.Auth.signUp("sabbagh", "Password123", options,
//                result -> Log.i("AuthQuickStart", "Result: " + result.toString()),
//                error -> Log.e("AuthQuickStart", "Sign up failed", error)
//        );

//        Amplify.Auth.confirmSignUp(
//                "sabbagh",
//                "850679",
//                result -> Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete"),
//                error -> Log.e("AuthQuickstart", error.toString())
//        );
//
//
//        Amplify.Auth.signIn(
//                "sabbagh",
//                "Password123",
//                result -> Log.i("AuthQuickstart", result.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete"),
//                error -> Log.e("AuthQuickstart", error.toString())
//        );
//        Amplify.Auth.fetchAuthSession(
//                result -> Log.i("AmplifyQuickstart", result.toString()),
//                error -> Log.e("AmplifyQuickstart", error.toString())
//        );


        findViewById(R.id.logoutButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amplify.Auth.signOut(
                        () -> Log.i("AuthQuickstart", "Signed out successfully"),
                        error -> Log.e("AuthQuickstart", error.toString())


                );


                Amplify.Auth.signOut(
                        AuthSignOutOptions.builder().globalSignOut(true).build(),
                        () -> Log.i("AuthQuickstart", "Signed out globally"),
                        error -> Log.e("AuthQuickstart", error.toString())
                );
            }
        });

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


    public void displaySignUpPage(View view) {
        Intent intent = new Intent(this, SignupPage.class);
        startActivity(intent);

    }

    public void displayLogInPage(View view) {
        Intent intent = new Intent(this, LogInPage.class);
        startActivity(intent);

    }


}
