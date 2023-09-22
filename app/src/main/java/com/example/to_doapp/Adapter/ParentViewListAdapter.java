package com.example.to_doapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.to_doapp.Model.TodoList;
import com.example.to_doapp.R;

import java.util.List;


public class ParentViewListAdapter extends RecyclerView.Adapter<ParentViewListViewHolder>{

    Context context;
    List<TodoList> items;

    public ParentViewListAdapter(Context context, List<TodoList> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ParentViewListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ParentViewListViewHolder(LayoutInflater.from(context).inflate(R.layout.parent_rv_view_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ParentViewListViewHolder holder, int position) {
        holder.title.setText(items.get(position).getTitle());

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
