package com.example.to_doapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.to_doapp.R;

import java.util.List;

public class CreateListAdapter extends RecyclerView.Adapter<CreateTaskViewHolder>{
    Context context;
    List<String> items;

    public CreateListAdapter(Context context, List<String> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public CreateTaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CreateTaskViewHolder(LayoutInflater.from(context).inflate(R.layout.create_task_recycler,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CreateTaskViewHolder holder, int position) {
        holder.txtTask.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

