package com.example.to_doapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewListActivity extends AppCompatActivity implements SelectItemListener, CustomDialog.DialogListener {

    TodoList todoList;
    RecyclerView recyclerView;
    TextView title;
    ViewTaskListAdapter adapter;
    TodoItem todoItem;
    ImageView imgBackArrow,imgUpdate;
    EditText editAddTask;
    Button taskBtn;
    CheckBox cbStatus;
    ArrayList<String> docIds;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); // This hides the title bar
        Intent i = new Intent(ViewListActivity.this, MainMenu.class);

        recyclerView = findViewById(R.id.recyclerView);
        title = findViewById(R.id.txt_title);
        imgBackArrow = findViewById(R.id.img_backArrow);
        imgUpdate = findViewById(R.id.img_updateList);
        editAddTask = findViewById(R.id.edit_addItem);
        taskBtn = findViewById(R.id.btn_addItem);
        cbStatus = findViewById(R.id.cb_taskStatus);
        db = FirebaseFirestore.getInstance();

        todoItem = new TodoItem();
        docIds = new ArrayList<>();


        Bundle extras = getIntent().getExtras();
        if (extras != null)
            todoList = (TodoList) extras.getSerializable("list");
        title.setText(todoList.getTitle());

        recyclerView.setLayoutManager(new LinearLayoutManager(ViewListActivity.this,LinearLayoutManager.VERTICAL,false));
        adapter = new ViewTaskListAdapter(getApplicationContext(),todoList.getTasks(),ViewListActivity.this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        editAddTask.setOnEditorActionListener((v, actionId, event) -> {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE))
                    performClick();
                return true;
            });

        imgBackArrow.setOnClickListener(x -> {
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        });

        performClick();
        imgUpdate.setOnClickListener(v -> {
            CollectionReference todoListRef = db.collection("taskLists");

            todoListRef.whereEqualTo("title",todoList.getTitle()).get()
                    .addOnCompleteListener(task -> {
                        for(QueryDocumentSnapshot document: task.getResult()){
                            docIds.add(document.getId());
                        }
                        docIds.forEach(id -> {
                            db.collection("taskLists").document(id).delete().addOnCompleteListener(taskDoc -> {
                                if (task.isSuccessful())
                                    Toast.makeText(ViewListActivity.this, "Successfully removed docs", Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(ViewListActivity.this, "Something went wrong deleting", Toast.LENGTH_SHORT).show();
                            });
                        });

                        HashMap<String, String> item = new HashMap<>();

                        todoList.getTasks().forEach(data ->{
                            item.put("title", todoList.getTitle());
                            item.put("task", data.getItem());
                            item.put("completedTask",Boolean.toString(data.isComplete()));
                            todoListRef.document().set(item).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {
                                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(i);
                                        finish();
                                    } else
                                        Toast.makeText(ViewListActivity.this, "Something went wrong adding", Toast.LENGTH_SHORT).show();
                                }
                            });
                            item.clear();
                        });
                    });
        });
    }

    public void performClick(){
        taskBtn.setOnClickListener(v -> {
            if (editAddTask.getText().toString().isEmpty())
                Toast.makeText(ViewListActivity.this, "Field is still empty", Toast.LENGTH_SHORT).show();
            TodoItem newTask = new TodoItem(editAddTask.getText().toString(), cbStatus.isChecked());
            todoList.getTasks().add(newTask);
            adapter.notifyItemInserted(todoList.getTasks().indexOf(newTask));
            editAddTask.setText("");

        });
    }

    @Override
    public void onItemClicked(TodoItem item) {
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

