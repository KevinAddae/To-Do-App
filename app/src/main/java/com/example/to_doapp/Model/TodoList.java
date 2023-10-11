package com.example.to_doapp.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class TodoList implements Serializable {

    private String title;
    private ArrayList<TodoItem> TodoItems;

    public TodoList(String title, ArrayList<TodoItem> TodoItems){
        this.title = title;
        this.TodoItems = TodoItems;
    }


    public TodoList(){
        title = "";
        TodoItems = new ArrayList<>();
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

