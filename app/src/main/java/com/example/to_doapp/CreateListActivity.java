package com.example.to_doapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.to_doapp.Adapter.CreateListAdapter;
import com.example.to_doapp.Model.TodoList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class CreateListActivity extends AppCompatActivity {

    private EditText title, editAddItem;
    private Button addItemBtn;
    private ImageView arrowBack, addList;
    private TodoList todoList;
    private RecyclerView recyclerView;
    private CreateListAdapter adapter;
    private FirebaseUser fUser;
    private FirebaseFirestore db;

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
        recyclerView = findViewById(R.id.recyclerView);
        editAddItem = findViewById(R.id.edit_addItem);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        todoList = new TodoList();

        adapter = new CreateListAdapter(getApplicationContext(),todoList.getTasks());

        recyclerView.setAdapter(adapter);

        fUser = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();


        arrowBack.setOnClickListener(v -> {
            Intent i = new Intent(CreateListActivity.this, MainMenu.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        });

        addItemBtn.setOnClickListener(v -> {
            todoList.getTasks().add(editAddItem.getText().toString());
            Toast.makeText(this, todoList.getTasks().size() + "", Toast.LENGTH_SHORT).show();
            adapter.notifyItemInserted(todoList.getTasks().size()-1);
        });

        addList.setOnClickListener(v -> {
            todoList.getTasks().forEach(task -> {
                DocumentReference reference = db.collection("taskLists").document();
                HashMap<String, Object> hashMap = new HashMap<>();

                hashMap.put("userId", fUser.getUid());
                hashMap.put("task", task);
                reference.set(hashMap);

                Intent i = new Intent(CreateListActivity.this, MainMenu.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();

            });
        });

    }
}