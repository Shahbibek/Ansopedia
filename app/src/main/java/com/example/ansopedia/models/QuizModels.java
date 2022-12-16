package com.example.ansopedia.models;

import java.util.HashMap;

public class QuizModels {
    int id,type,level,score;
    String description,image_url;
    String slug;
    String content;
    HashMap<String,String> map;

    public QuizModels() {
    }

    public QuizModels(int id, int type, int level, int score, String description, String content) {
        this.id = id;
        this.type = type;
        this.level = level;
        this.score = score;
        this.description = description;
        this.content = content;
    }

    public QuizModels(int id, int type, int level, int score, String description, String image_url, String slug, String content, HashMap<String, String> map) {
        this.id = id;
        this.type = type;
        this.level = level;
        this.score = score;
        this.description = description;
        this.image_url = image_url;
        this.slug = slug;
        this.content = content;
        this.map = map;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public HashMap<String, String> getMap() {
        return map;
    }

    public void setMap(HashMap<String, String> map) {
        this.map = map;
    }
}
