package com.example.ansopedia.models;

public class TabsModel {
    private int id;
    private String content;
    public TabsModel(int id, String content) {
        this.id = id;
        this.content = content;


    }
    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
