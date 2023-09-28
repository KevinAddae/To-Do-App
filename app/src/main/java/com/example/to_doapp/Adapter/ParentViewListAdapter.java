package com.example.to_doapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.to_doapp.Model.TodoList;
import com.example.to_doapp.R;

import java.util.List;


public class ParentViewListAdapter extends RecyclerView.Adapter<ParentViewListViewHolder>{

    Context context;
    List<TodoList> items;
    SelectListener listener;
    public ParentViewListAdapter(Context context, List<TodoList> items, SelectListener listener) {
        this.context = context;
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ParentViewListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ParentViewListViewHolder(LayoutInflater.from(context).inflate(R.layout.parent_rv_view_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ParentViewListViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(items.get(position).getTitle());
        holder.container.setOnClickListener(v -> listener.onItemClicked(items.get(position)));

        ChildViewListAdapter childAdapter = new ChildViewListAdapter(context, items.get(position).getTaskItems());
        holder.rv_child.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false));
        holder.rv_child.setAdapter(childAdapter);
        childAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
