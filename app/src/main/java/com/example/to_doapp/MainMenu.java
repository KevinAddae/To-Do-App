package com.example.to_doapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.to_doapp.Adapter.ParentViewListAdapter;
import com.example.to_doapp.Adapter.SelectListener;
import com.example.to_doapp.Model.TodoList;
import com.example.to_doapp.Model.TodoItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainMenu extends AppCompatActivity implements SelectListener {

    private ImageView addList;
    private RecyclerView recyclerView;
    private ArrayList<TodoList> todoLists;
    private FirebaseFirestore db;
    private ArrayList<TodoItem> tasks;
    private ArrayList<String> titles;

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
        titles = new ArrayList<>();

        db.collection("taskLists").get().addOnCompleteListener(task1 -> {
            for(QueryDocumentSnapshot document: task1.getResult()){
                titles.add(document.get("title").toString());
                Log.e("MainMenu",titles.get(0) + "");
            }
            titles.stream().distinct().forEach(title -> db.collection("taskLists").whereEqualTo("title",title).get().addOnCompleteListener(task -> {
                String id = "";
                for (QueryDocumentSnapshot document: task.getResult()){
                    if (task.isSuccessful())
                        tasks.add(new TodoItem(document.get("task").toString(),Boolean.parseBoolean(document.get("completedTask").toString())));
                    id = document.get("userId").toString();
                }
                Log.e("MainMenu", "" + tasks.size());
                todoLists.add(new TodoList(title, tasks, id));
                tasks = new ArrayList<>();


                Toast.makeText(this, todoLists.size() + "", Toast.LENGTH_SHORT).show();
                ParentViewListAdapter adapter = new ParentViewListAdapter(MainMenu.this,todoLists,this);
                recyclerView.setLayoutManager(new LinearLayoutManager(  MainMenu.this,LinearLayoutManager.HORIZONTAL,false));
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }));

        });
        addList.setOnClickListener(v -> {
            Intent i = new Intent(MainMenu.this, CreateListActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        });

    }

    @Override
    public void onItemClicked(TodoList list) {
        Intent i = new Intent(MainMenu.this, ViewListActivity.class);
        i.putExtra("list",list);
        startActivity(i);
    }

}