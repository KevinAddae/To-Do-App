package com.example.to_doapp.Adapter;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.to_doapp.R;

public class ParentViewListViewHolder extends RecyclerView.ViewHolder {
    RecyclerView rv_child;
    TextView title;
    RelativeLayout container;

    public ParentViewListViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.rv_title);
        rv_child = itemView.findViewById(R.id.childRecyclerview);
        container = itemView.findViewById(R.id.Main_container);
    }
}
