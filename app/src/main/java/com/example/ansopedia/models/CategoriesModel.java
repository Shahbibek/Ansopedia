package com.example.ansopedia.models;

public class CategoriesModel {
   private int id, parent_id;
   private String content, short_desc, image_url, color;
    public CategoriesModel(int id, int parent_id, String content, String desc, String image_url, String color) {
        this.id = id;
        this.parent_id = parent_id;
        this.content = content;
        this.short_desc = desc;
        this.image_url = image_url;
        this.color = color;
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

    public String getImage_url() {
        return image_url;
    }

    public String getColor() {
        return color;
    }


}
