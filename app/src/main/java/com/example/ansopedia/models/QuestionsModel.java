package com.example.ansopedia.models;

public class QuestionsModel {
    private int id, type, level, score;
    private String description, content;

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public int getScore() {
        return score;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }

    public QuestionsModel(int id, int type, int level, int score, String description, String content) {
        this.id = id;
        this.type = type;
        this.level = level;
        this.score = score;
        this.description = description;
        this.content = content;
    }
}
