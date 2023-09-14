package com.example.to_doapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.to_doapp.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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

        loginBtn.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        registerBtn.setOnClickListener(v -> {
            String txtEmail = email.getText().toString().trim();
            String txtUsername = username.getText().toString().trim();
            String txtPassword = password.getText().toString().trim();

            if (txtEmail.isEmpty() || txtPassword.isEmpty() || txtUsername.isEmpty()) {
                Toast.makeText(this, "Ensure all fields are complete", Toast.LENGTH_SHORT).show();
            } else if (txtPassword.length() < 6)
                Toast.makeText(this, "The password length is too short", Toast.LENGTH_SHORT).show();
            else
                auth.createUserWithEmailAndPassword(txtEmail,txtPassword).addOnCompleteListener(task -> {
                   if(task.isSuccessful()){
                        sendUserInfo();
                       Toast.makeText(this, "Auth success", Toast.LENGTH_SHORT).show();
                   }
                   else
                       Toast.makeText(this, "Auth Failed", Toast.LENGTH_SHORT).show();
                });
        });
    }

    private void sendUserInfo() {
    }
}