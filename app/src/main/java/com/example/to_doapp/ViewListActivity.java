package com.example.to_doapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.to_doapp.Model.TodoList;

public class ViewListActivity extends AppCompatActivity {

    TodoList todoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
            todoList = (TodoList) extras.getSerializable("list");
        Toast.makeText(this, todoList.getTitle(), Toast.LENGTH_SHORT).show();
    }
}