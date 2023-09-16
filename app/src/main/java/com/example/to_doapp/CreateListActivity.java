package com.example.to_doapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class CreateListActivity extends AppCompatActivity {

    private EditText title;
    private Button addItemBtn;
    private ImageView arrowBack, addList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); // This hides the title bar

        title = findViewById(R.id.edit_title);
        addItemBtn = findViewById(R.id.btn_addItem);

        arrowBack = findViewById(R.id.img_backArrow);
        addList = findViewById(R.id.img_addList);

        arrowBack.setOnClickListener(v -> {
            Intent i = new Intent(CreateListActivity.this, MainMenu.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        });

    }
}