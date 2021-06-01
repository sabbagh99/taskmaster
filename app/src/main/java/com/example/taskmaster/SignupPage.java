package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;

public class SignupPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);


//        try {
//            Amplify.addPlugin(new AWSCognitoAuthPlugin());
//            Amplify.configure(getApplicationContext());
//
//            Log.i("Tutorial", "Initialized Amplify");
//        } catch (AmplifyException e) {
//            Log.e("Tutorial", "Could not initialize Amplify", e);
//        }
        findViewById(R.id.signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EditText user = findViewById(R.id.userName_signUp);
                String userName = user.getText().toString();
                EditText userPass = findViewById(R.id.password_signUp);
                String passWord = userPass.getText().toString();

                EditText userEmail = findViewById(R.id.email_signUp);
                String email = userEmail.getText().toString();
                Log.i("email", "onClick: "+email);
                AuthSignUpOptions options = AuthSignUpOptions.builder()
                        .userAttribute(AuthUserAttributeKey.email(), email)
                        .build();
                Amplify.Auth.signUp(userName, passWord, options,
                        result -> Log.i("AuthQuickStart", "Result: " + result.toString()),
                        error -> Log.e("AuthQuickStart", "Sign up failed", error)
                );


                Intent intent = new Intent(v.getContext(), confirmSignUpPage.class);
                intent.putExtra("userName2",userName);

                startActivity(intent);
            }
        });



    }


}