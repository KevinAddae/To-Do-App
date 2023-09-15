package com.example.to_doapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.to_doapp.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private FirebaseAuth auth;

    private Button registerBtn,loginBtn;
    private EditText email, password, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); // This hides the title bar

        registerBtn = findViewById(R.id.btn_register);
        loginBtn = findViewById(R.id.btn_login);

        email = findViewById(R.id.edit_email);
        password = findViewById(R.id.edit_password);
        username = findViewById(R.id.edit_username);

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
        String userID = auth.getCurrentUser().getUid();
        DocumentReference documentReference = db.collection("users").document(userID);

        HashMap<String, String> user = new HashMap<>();
        user.put("id", userID);
        user.put("username", username.getText().toString().trim());

        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(RegisterActivity.this, "DocumentSnapshot added with ID: " + documentReference.getId(), Toast.LENGTH_LONG).show();
            }
        });

        Intent intent = new Intent(RegisterActivity.this, MainMenu.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }
}