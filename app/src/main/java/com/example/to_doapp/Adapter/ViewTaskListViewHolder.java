package com.example.to_doapp.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.to_doapp.R;

public class ViewTaskListViewHolder extends RecyclerView.ViewHolder {
    TextView txtTask, txtStatus;
    RelativeLayout container;
    ImageView imgDeleteTask;
    public ViewTaskListViewHolder(@NonNull View itemView) {
        super(itemView);
        txtTask = itemView.findViewById(R.id.txt_task);
        txtStatus = itemView.findViewById(R.id.txtStatus);
        container = itemView.findViewById(R.id.container);
        imgDeleteTask = itemView.findViewById(R.id.img_delete);
    }
}
