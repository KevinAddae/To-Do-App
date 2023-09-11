package com.example.to_doapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * This Class is the and its connected xml file is the launching screen. This class aims to take the user information inputted and check
 * The user information using firebase auth. If the information is correct the user will be sent to the main menu activity.
 * Otherwise, on the occasion the user does not have an account the user can be sent to the activity that is responsible for creating accounts.
 *
 */
public class LoginActivity extends AppCompatActivity {

    private EditText email,pass;
    private Button loginBtn, registerBtn;
    private CheckBox rememberMe;
    private TextView forgetMe;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); // This hides the title bar

        auth = FirebaseAuth.getInstance();

        email = findViewById(R.id.edit_email);
        pass = findViewById(R.id.edit_password);

        loginBtn = findViewById(R.id.btn_login);
        registerBtn = findViewById(R.id.btn_register);

/**
 * Create Firebase project for this, connect accounts the continue user validation.
 */
        loginBtn.setOnClickListener(v -> {
            if (email.getText().toString().isEmpty() || pass.getText().toString().isEmpty())
                Toast.makeText(LoginActivity.this, "Please Complete All Fields", Toast.LENGTH_LONG).show();
            else {
                auth.signInWithEmailAndPassword(email.getText().toString(),pass.getText().toString()).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Intent i = new Intent(LoginActivity.this, MainMenu.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        finish();
                    } else
                        Toast.makeText(this, "Incorrect Password or Email, Please try again", Toast.LENGTH_SHORT).show();
                });
            }
        });

        registerBtn.setOnClickListener(v -> {
            Intent i = new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(i);
            finish();

        });
    }

}