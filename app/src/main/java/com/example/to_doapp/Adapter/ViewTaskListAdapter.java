package com.example.to_doapp.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.to_doapp.Model.TodoItem;
import com.example.to_doapp.Model.TodoList;
import com.example.to_doapp.R;

import java.util.List;

public class ViewTaskListAdapter extends RecyclerView.Adapter<ViewTaskListViewHolder> {

    Context context;
    List<TodoItem> items;
    SelectItemListener listener;


    public ViewTaskListAdapter(Context context, List<TodoItem> items, SelectItemListener listener) {
        this.context = context;
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewTaskListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewTaskListViewHolder(LayoutInflater.from(context).inflate(R.layout.view_tasks_rv,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewTaskListViewHolder holder, int position) {
        holder.txtTask.setText(items.get(position).getItem());
        if(items.get(position).isComplete()){
            holder.txtStatus.setText("Completed");
            holder.txtStatus.setTextColor(Color.parseColor("#96DD28"));
        } else {
            holder.txtStatus.setText("incomplete");
            holder.txtStatus.setTextColor(Color.parseColor("#E6221F"));
        }

        holder.container.setOnClickListener(v -> listener.onItemClicked(items.get(position).getItem()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
