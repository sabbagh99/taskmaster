package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DisplaySettingsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_settings_page);
    }

    public void showUserName(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        EditText userNameField = (EditText) findViewById(R.id.userName);
        String userName = userNameField.getText().toString();
        intent.putExtra("userName", userName);
        startActivity(intent);
    }

}
