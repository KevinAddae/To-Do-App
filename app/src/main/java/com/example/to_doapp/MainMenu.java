package com.example.to_doapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.to_doapp.Adapter.ParentViewListAdapter;
import com.example.to_doapp.Model.TodoList;
import com.example.to_doapp.Model.TodoItem;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class MainMenu extends AppCompatActivity {

    private ImageView addList;
    private RecyclerView recyclerView;
    private ArrayList<TodoList> todoLists;
    private FirebaseFirestore db;
    private ArrayList<TodoItem> tasks;

    @SuppressLint({"MissingInflatedId", "SuspiciousIndentation"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); // This hides the title bar

        addList = findViewById(R.id.img_addList);
        recyclerView = findViewById(R.id.recyclerView);
        db = FirebaseFirestore.getInstance();
        todoLists = new ArrayList<>();
        tasks = new ArrayList<>();

        addList.setOnClickListener(v -> {
            Intent i = new Intent(MainMenu.this, CreateListActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();


        });

        db.collection("taskLists").get().addOnCompleteListener(task -> {
            String dbTitle = "";
            String id = "";
            for (QueryDocumentSnapshot document : task.getResult()) {
                //if (!dbTitle.equals(document.get("title").toString()) || !dbTitle.isEmpty()) {
                    //todoLists.add(new TodoList(dbTitle,tasks))
                    todoLists.add(new TodoList(document.get("title").toString(), tasks, document.get("userId").toString()));
                    //tasks.clear();
                //}
                tasks.add(new TodoItem(document.get("task").toString(),Boolean.parseBoolean(document.get("completedTask").toString())));
                dbTitle = document.get("title").toString();
                id = document.get("userId").toString();

            }
            if (todoLists.size() == 0)
                todoLists.add(new TodoList(dbTitle, tasks, id));

            TodoList a = new TodoList();
            a.setTitle("Test");
            todoLists.add(a);
            Toast.makeText(this, todoLists.size() + "", Toast.LENGTH_SHORT).show();
            ParentViewListAdapter adapter = new ParentViewListAdapter(MainMenu.this,todoLists);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainMenu.this,LinearLayoutManager.VERTICAL,false));
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        });

    }
}