package com.example.to_doapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.to_doapp.Adapter.ViewTaskListAdapter;
import com.example.to_doapp.Model.TodoList;

public class ViewListActivity extends AppCompatActivity {

    TodoList todoList;
    RecyclerView recyclerView;
    TextView title;
    ViewTaskListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); // This hides the title bar

        recyclerView = findViewById(R.id.recyclerView);
        title = findViewById(R.id.txt_title);


        Bundle extras = getIntent().getExtras();
        if (extras != null)
            todoList = (TodoList) extras.getSerializable("list");
        Toast.makeText(this, todoList.getTitle(), Toast.LENGTH_SHORT).show();
        title.setText(todoList.getTitle());

        recyclerView.setLayoutManager(new LinearLayoutManager(ViewListActivity.this,LinearLayoutManager.VERTICAL,false));
        adapter = new ViewTaskListAdapter(getApplicationContext(),todoList.getTasks());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        ImageView imgBackArrow = findViewById(R.id.img_backArrow);
        imgBackArrow.setOnClickListener(v -> {
            Intent i = new Intent(ViewListActivity.this, MainMenu.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        });


    }

}