package com.example.administrator.myapplication.model;

public class Homework {
    int studentId;
    String name;
    String score;

    public Homework() {
    }

    public Homework(int studentId, String name, String score) {
        this.studentId = studentId;
        this.name = name;
        this.score = score;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
