package com.example.to_doapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
    private TextView editRecyclerTitle;
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

        ArrayList<String> list = new ArrayList<>();

        adapter = new CreateListAdapter(getApplicationContext(),list);

        recyclerView.setAdapter(adapter);

        editRecyclerTitle = findViewById(R.id.txt_title_recyclerView);

        fUser = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();

        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (editRecyclerTitle.getText().equals(""))
                    editRecyclerTitle.setText("Title");
                else {
                    editRecyclerTitle.setText("");
                    editRecyclerTitle.setText(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        arrowBack.setOnClickListener(v -> {
            Intent i = new Intent(CreateListActivity.this, MainMenu.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        });

        addItemBtn.setOnClickListener(v -> {
            list.add(editAddItem.getText().toString());
            Toast.makeText(this, list.size() + "", Toast.LENGTH_SHORT).show();
            adapter.notifyItemInserted(list.size()-1);
            editAddItem.setText("");
        });

        addList.setOnClickListener(v -> {
            if (title.getText().equals(""))
                Toast.makeText(this, "Please select a title", Toast.LENGTH_SHORT).show();
            else if (list.isEmpty())
                Toast.makeText(this, "Make sure you have added tasks", Toast.LENGTH_SHORT).show();
            else {
                list.forEach(task -> {
                    DocumentReference reference = db.collection("taskLists").document();
                    HashMap<String, Object> hashMap = new HashMap<>();

                    hashMap.put("userId", fUser.getUid());
                    hashMap.put("title", title.getText().toString());
                    hashMap.put("task", task);
                    hashMap.put("completedTask","false");
                    reference.set(hashMap);

                    Intent i = new Intent(CreateListActivity.this, MainMenu.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    finish();
                });
            }
        });

    }
}