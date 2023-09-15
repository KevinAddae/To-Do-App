package com.example.to_doapp.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class todoList implements Serializable {

    private String title;
    private ArrayList<String> items;
    private Integer recyclerBgColour;
    private Integer textColour;

    public todoList(String title, ArrayList<String> items, Integer recyclerBgColour, Integer textColour){
        this.title = title;
        this.items = items;
        this.recyclerBgColour = recyclerBgColour;
        this.textColour = textColour;
    }

    public todoList(){
        title = "";
        items = new ArrayList<>();
        recyclerBgColour = 0;
        textColour = 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getItems() {
        return items;
    }

    public void setItems(ArrayList<String> items) {
        this.items = items;
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

