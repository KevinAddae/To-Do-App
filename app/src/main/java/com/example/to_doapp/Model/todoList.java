package com.example.to_doapp.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class todoList implements Serializable {

    private String title;
    private ArrayList<String> tasks;
    private ArrayList<String> completeTasks;
    private Integer recyclerBgColour;
    private Integer textColour;

    public todoList(String title, ArrayList<String> tasks, ArrayList<String> completeTasks,
                    Integer recyclerBgColour, Integer textColour){
        this.title = title;
        this.tasks = tasks;
        this.completeTasks = completeTasks;
        this.recyclerBgColour = recyclerBgColour;
        this.textColour = textColour;
    }


    public todoList(){
        title = "";
        tasks = new ArrayList<>();
        completeTasks = new ArrayList<>();
        recyclerBgColour = 0;
        textColour = 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public ArrayList<String> getCompleteTasks() {
        return completeTasks;
    }

    public void setCompleteTasks(ArrayList<String> completeTasks) {
        this.completeTasks = completeTasks;
    }

    public ArrayList<String> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<String> tasks) {
        this.tasks = tasks;
    }

    public Integer getRecyclerBgColour() {
        return recyclerBgColour;
    }

    public void setRecyclerBgColour(Integer recyclerBgColour) {
        this.recyclerBgColour = recyclerBgColour;
    }

    public Integer getTextColour() {
        return textColour;
    }

    public void setTextColour(Integer textColour) {
        this.textColour = textColour;
    }
}

