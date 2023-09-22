package com.example.to_doapp.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class TodoList implements Serializable {

    private String title;
    private ArrayList<TodoItem> TodoItems;
    private String userID;

    public TodoList(String title, ArrayList<TodoItem> TodoItems, String userID){
        this.title = title;
        this.TodoItems = TodoItems;
        this.userID = userID;
    }


    public TodoList(){
        title = "";
        TodoItems = new ArrayList<>();
        userID = "";
    }

    public TodoList(String title, ArrayList<TodoItem> tasks){

    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public ArrayList<TodoItem> getTasks() {
        return TodoItems;
    }

    public ArrayList<String> getTaskItems() {
        ArrayList<String> strItems = new ArrayList<>();

        TodoItems.forEach(TodoItem ->
                strItems.add(TodoItem.getItem()));
        return strItems;
    }

    public void setTasks(ArrayList<TodoItem> TodoItems) {
        this.TodoItems = TodoItems;
    }

}

