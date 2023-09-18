package com.example.to_doapp.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class TodoList implements Serializable {

    private String title;
    private ArrayList<String> tasks;
    private ArrayList<String> completeTasks;
    private String userID;

    public TodoList(String title, ArrayList<String> tasks, ArrayList<String> completeTasks, String userID){
        this.title = title;
        this.tasks = tasks;
        this.completeTasks = completeTasks;
        this.userID = userID;
    }


    public TodoList(){
        title = "";
        tasks = new ArrayList<>();
        completeTasks = new ArrayList<>();
        userID = "";
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

}

