package com.example.to_doapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * This Class is the and its connected xml file is the launching screen. This class aims to take the user information inputted and check
 * The user information using firebase auth. If the information is correct the user will be sent to the main menu activity.
 *  Otherwise, on the occasion the user does not have an account the user can be sent to the activity that is responsible for creating accounts.
 *
 */
public class LoginActivity extends AppCompatActivity {

    private TextView email,pass;
    private Button loginBtn, registerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); // This hides the title bar





    }
}