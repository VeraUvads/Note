package com.example.notedelete;

public class Note {
    private int id;
    private String title;
    private String description;
    private int priority;
    private int data;


    public Note(int id, String title, String description, int priority, int data) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.data = data;
    }

    public int getId() {
        return id;
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

    public int getData() {
        return data;
    }

    public static String getDayAsString(int position) {
        switch (position) {
            case 1:
                return "Понедельник";
            case 2:
                return "Вторник";
            case 3:
                return "Среда";
            case 4:
                return "Четверг";
            case 5:
                return "Пятница";
            case 6:
                return "Суббота";
            default:
                return "Воскресенье";


        }
    }
}
