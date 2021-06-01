package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.amplifyframework.core.Amplify;

public class LogInPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);

findViewById(R.id.login_button).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {



        EditText user = findViewById(R.id.userName_login);
        String userName = user.getText().toString();

        EditText userPass = findViewById(R.id.passWord_login);
        String passWord = userPass.getText().toString();


        Amplify.Auth.signIn(
                userName,
                passWord,
                result -> Log.i("AuthQuickstart", result.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete"),
                error -> Log.e("AuthQuickstart", error.toString())
        );
        Amplify.Auth.fetchAuthSession(
                result -> Log.i("AmplifyQuickstart", result.toString()),
                error -> Log.e("AmplifyQuickstart", error.toString())
        );


        Intent intent = new Intent(v.getContext(), MainActivity.class);
        intent.putExtra("userName1",userName);
        startActivity(intent);


    }
});


    }
}