package com.example.to_doapp.Model;

public class TodoItem {

    private String item;
    private boolean complete;

    public TodoItem(String item, boolean complete) {
        this.item = item;
        this.complete = complete;
    }

    public TodoItem() {
        item = "";
        complete = false;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
