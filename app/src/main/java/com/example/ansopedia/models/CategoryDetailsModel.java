package com.example.ansopedia.models;

public class CategoryDetailsModel {
    private int id, parent_id;
    private String content, short_desc;
    public CategoryDetailsModel(int id, int parent_id, String content, String desc) {
        this.id = id;
        this.parent_id = parent_id;
        this.content = content;
        this.short_desc = desc;

    }
    public int getId() {
        return id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public String getContent() {
        return content;
    }

    public String getDesc() {
        return short_desc;
    }


}
