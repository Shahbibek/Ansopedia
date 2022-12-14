package com.example.ansopedia.models;

public class CategoryDetailsModel {
    String topicName;
    int id;

    public CategoryDetailsModel(){}
    public CategoryDetailsModel(String topicName, int id) {
        this.topicName = topicName;
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
