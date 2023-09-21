package com.example.to_doapp.Adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.to_doapp.R;

public class ChildViewTaskViewHolder extends RecyclerView.ViewHolder {

    TextView txtItem;

    public ChildViewTaskViewHolder(@NonNull View itemView) {
        super(itemView);
        txtItem = itemView.findViewById(R.id.txt_item);

    }
}
