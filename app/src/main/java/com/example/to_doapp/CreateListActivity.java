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

import com.example.to_doapp.Adapter.CreateListAdapter;
import com.example.to_doapp.Model.TodoList;

import java.util.ArrayList;

public class CreateListActivity extends AppCompatActivity {

    private EditText title;
    private Button addItemBtn;
    private ImageView arrowBack, addList;
    private TodoList todoList;
    private RecyclerView recyclerView;
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

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<String> a = new ArrayList();
        a.add("I dunnk");
        todoList = new TodoList();
        todoList.setTasks(a);

        recyclerView.setAdapter(new CreateListAdapter(getApplicationContext(),todoList.getTasks()));



        arrowBack.setOnClickListener(v -> {
            Intent i = new Intent(CreateListActivity.this, MainMenu.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        });

        addItemBtn.setOnClickListener(v -> {
            showAddItemDialog();
        });

    }

    private void showAddItemDialog() {
        Dialog dialog = new Dialog(CreateListActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        setContentView(R.layout.add_item_dialog);

        final EditText editAddItem = findViewById(R.id.edit_addItem);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})  Button cancelBtn = findViewById(R.id.btn_cancel);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})  Button acceptBtn = findViewById(R.id.btn_accept);

        cancelBtn.setOnClickListener(v -> {
            dialog.dismiss();
        });

        acceptBtn.setOnClickListener(v -> {
            todoList.getTasks().add(editAddItem.getText().toString());
            dialog.dismiss();
        });
        dialog.show();

    }
}