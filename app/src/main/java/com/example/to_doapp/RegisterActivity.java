package com.example.to_doapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.to_doapp.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private User user;
    private Button registerBtn,loginBtn;
    private EditText email, password, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerBtn.findViewById(R.id.btn_register);
        loginBtn.findViewById(R.id.btn_login);

        email.findViewById(R.id.edit_email);
        password.findViewById(R.id.edit_password);
        username.findViewById(R.id.edit_username);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();


    }
}