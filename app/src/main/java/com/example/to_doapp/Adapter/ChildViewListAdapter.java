package com.example.to_doapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.to_doapp.R;

import java.util.List;


public class ChildViewListAdapter extends RecyclerView.Adapter<ChildViewTaskViewHolder>{

    Context context;
    List<String> items;

    public ChildViewListAdapter(Context context, List<String> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ChildViewTaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChildViewTaskViewHolder(LayoutInflater.from(context).inflate(R.layout.child_rv_view_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewTaskViewHolder holder, int position) {
        holder.txtItem.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
