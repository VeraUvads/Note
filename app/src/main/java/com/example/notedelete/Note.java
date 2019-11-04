package com.example.notedelete;

public class Note {
    private String title;
    private String description;
    private int priority;
    private String data;


    public Note(String title, String description, int priority, String data) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public String getData() {
        return data;
    }
}
