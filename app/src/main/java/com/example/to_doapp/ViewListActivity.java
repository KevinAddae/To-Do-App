package com.example.to_doapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.to_doapp.Adapter.SelectItemListener;
import com.example.to_doapp.Adapter.ViewTaskListAdapter;
import com.example.to_doapp.Model.TodoItem;
import com.example.to_doapp.Model.TodoList;

public class ViewListActivity extends AppCompatActivity implements SelectItemListener, CustomDialog.DialogListener {

    TodoList todoList;
    RecyclerView recyclerView;
    TextView title;
    ViewTaskListAdapter adapter;
    TodoItem todoItem;
    ImageView imgBackArrow,imgUpdate;
    EditText editAddTask;
    Button Taskbtn;
    CheckBox cbStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); // This hides the title bar

        recyclerView = findViewById(R.id.recyclerView);
        title = findViewById(R.id.txt_title);
        imgBackArrow = findViewById(R.id.img_backArrow);
        imgUpdate = findViewById(R.id.img_updateList);
        editAddTask = findViewById(R.id.edit_addItem);
        Taskbtn = findViewById(R.id.btn_addItem);
        cbStatus = findViewById(R.id.cb_taskStatus);


        todoItem = new TodoItem();


        Bundle extras = getIntent().getExtras();
        if (extras != null)
            todoList = (TodoList) extras.getSerializable("list");
        title.setText(todoList.getTitle());

        recyclerView.setLayoutManager(new LinearLayoutManager(ViewListActivity.this,LinearLayoutManager.VERTICAL,false));
        adapter = new ViewTaskListAdapter(getApplicationContext(),todoList.getTasks(),ViewListActivity.this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        ImageView imgBackArrow = findViewById(R.id.img_backArrow);
        imgBackArrow.setOnClickListener(v -> {
            Intent i = new Intent(ViewListActivity.this, MainMenu.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        });

    Taskbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (editAddTask.getText().toString().isEmpty())
                Toast.makeText(ViewListActivity.this, "Field is still empty", Toast.LENGTH_SHORT).show();
            TodoItem newTask = new TodoItem(editAddTask.getText().toString(), cbStatus.isChecked());
            todoList.getTasks().add(newTask);
            adapter.notifyItemInserted(todoList.getTasks().indexOf(newTask));
            editAddTask.setText("");

        }
    });
    }

    @Override
    public void onItemClicked(TodoItem item) {
        Toast.makeText(this, "You tapped "+ item.getItem(), Toast.LENGTH_SHORT).show();
        CustomDialog dialog = new CustomDialog();
        dialog.show(getSupportFragmentManager(),"custom dialog");
        todoItem = item;
    }

    @Override
    public void applyText(String task,Boolean status) {
        todoList.getTasks().set(todoList.getTasks().indexOf(todoItem),new TodoItem(task,status));
        adapter.notifyDataSetChanged();
    }
}

